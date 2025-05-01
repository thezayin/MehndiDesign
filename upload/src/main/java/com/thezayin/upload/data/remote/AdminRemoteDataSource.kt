package com.thezayin.upload.data.remote

import android.content.Context
import android.net.Uri
import com.thezayin.framework.remote.SupabaseApiClient
import com.thezayin.upload.domain.model.ImageModel
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class AdminRemoteDataSource(
    supabaseApiClient: SupabaseApiClient,
    private val context: Context
) {

    private val storageClient = supabaseApiClient.sup().storage
    private val postgrest = supabaseApiClient.sup().postgrest

    /**
     * Uploads a list of images to Supabase Storage and inserts their URLs into the specified table.
     *
     * @param uris List of image URIs to upload.
     * @param tableName The name of the table to insert image URLs into.
     * @return Result containing a list of inserted ImageModel objects or an error.
     */
    // Inside AdminRemoteDataSource class
    suspend fun uploadImages(uris: List<Uri>, tableName: String): Result<List<ImageModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val imageModels = mutableListOf<ImageModel>()
                for (uri in uris) {
                    val fileName = "${UUID.randomUUID()}.jpg"
                    val filePath = "$tableName/$fileName"

                    // Convert Uri to ByteArray
                    val byteArray =
                        uriToByteArray(uri) ?: throw Exception("Failed to convert URI to ByteArray")

                    // Upload the image
                    storageClient.from("henna-images").upload(filePath, byteArray)

                    // Retrieve the public URL
                    val publicUrl = storageClient.from("henna-images").publicUrl(filePath)

                    // Create an ImageModel object
                    val imageModel = ImageModel(
                        id = 0, // Assuming auto-increment in DB
                        imageUrl = publicUrl,
                        category = tableName
                    )
                    imageModels.add(imageModel)
                }

                // Insert into the specified table and decode the response to List<ImageModel>
                val insertResponse =
                    postgrest.from(tableName).insert(imageModels).decodeList<ImageModel>()

                Result.success(insertResponse)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    private fun uriToByteArray(uri: Uri): ByteArray? {
        return try {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                inputStream.readBytes()
            }
        } catch (e: Exception) {
            null
        }
    }
}
