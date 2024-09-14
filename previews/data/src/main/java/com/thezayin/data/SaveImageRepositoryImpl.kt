package com.thezayin.data

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.thezayin.domain.repository.SaveImageRepository
import com.thezayin.framework.utils.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

/**
 * Implementation of the [SaveImageRepository] interface.
 * Handles the process of saving an image from a URL to internal storage.
 */
class SaveImageRepositoryImpl(private val context: Context) : SaveImageRepository {

    private val client = HttpClient(CIO) {
        // Optional: Configure timeouts, logging, etc.
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
            connectTimeoutMillis = 15000
            socketTimeoutMillis = 15000
        }
    }

    /**
     * Saves the image from the provided URL and returns the result as a [Flow] of [Response].
     * The response can represent the loading, success, or error states.
     *
     * @param url The URL of the image to be saved.
     * @return A [Flow] that emits [Response] containing the saved image URI or an error message.
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun saveImage(url: String): Flow<Response<String>> = flow {
        try {
            emit(Response.Loading)  // Emit loading state

            // Download the image bytes
            val imageBytes = downloadImage(url)

            // Save the image and get the saved URI
            val savedImageUri = saveImageToStorage(url, imageBytes)

            emit(Response.Success(savedImageUri))  // Emit success with URI
        } catch (e: Exception) {
            // Emit error state with an exception message, or a generic message if null
            emit(
                Response.Error(
                    e.localizedMessage ?: "An error occurred while saving the image"
                )
            )
        }
    }.flowOn(Dispatchers.IO)  // Perform on IO dispatcher

    /**
     * Downloads the image from the given URL and returns the byte array.
     */
    @Throws(IOException::class)
    private suspend fun downloadImage(url: String): ByteArray {
        val response = client.get(url) {
            // Optional: Add headers if necessary
            headers {
                append(HttpHeaders.Accept, "image/*")
            }
        }

        if (!response.status.isSuccess()) {
            throw IOException("Failed to download image: ${response.status}")
        }

        return response.body<ByteArray>()
    }

    /**
     * Saves the image byte array to the device's storage and returns the URI as a string.
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    @Throws(IOException::class)
    private fun saveImageToStorage(url: String, imageBytes: ByteArray): String {
        val filename = url.substringAfterLast("/").substringBefore("?")
        val mimeType = getMimeType(filename) ?: "image/jpeg"

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, filename)
            put(MediaStore.Images.Media.MIME_TYPE, mimeType)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
        }

        val resolver = context.contentResolver
        val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        val imageUri = resolver.insert(collection, contentValues)
            ?: throw IOException("Failed to create new MediaStore record.")

        resolver.openOutputStream(imageUri)?.use { outputStream ->
            outputStream.write(imageBytes)
        } ?: throw IOException("Failed to open output stream.")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            contentValues.clear()
            contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
            resolver.update(imageUri, contentValues, null, null)
        }

        return imageUri.toString()
    }

    /**
     * Determines the MIME type based on the file extension.
     */
    private fun getMimeType(filename: String): String? {
        return when (filename.substringAfterLast('.', "").lowercase()) {
            "jpg", "jpeg" -> "image/jpeg"
            "png" -> "image/png"
            "gif" -> "image/gif"
            "bmp" -> "image/bmp"
            "webp" -> "image/webp"
            else -> null
        }
    }
}