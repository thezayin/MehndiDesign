package com.thezayin.favorites.data

import android.content.Context
import com.google.gson.Gson
import com.thezayin.favorites.domain.repository.FavoriteRepository
import com.thezayin.framework.model.Image
import com.thezayin.framework.utils.Response
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.io.File

/**
 * Implementation of the [FavoriteRepository] interface for managing user's favorite images.
 * This repository handles storing, retrieving, and deleting favorite images locally.
 *
 * @param context The application context, used to access local file storage.
 */
class FavoriteRepositoryImpl(
    context: Context
) : FavoriteRepository {

    // Directory for storing the liked images and metadata files
    private val likedDir: File = File(
        context.getExternalFilesDir(null) ?: context.filesDir, // Default to external files dir, fallback to internal
        "liked_images"
    ).apply {
        // Ensure the directory exists, if not, create it along with a .nomedia file to prevent media scanner
        if (!exists()) {
            Timber.d("Creating favorites folder at $absolutePath")
            mkdirs()
            File(this, ".nomedia").takeIf { !it.exists() }?.also {
                Timber.d("Touching .nomedia to hide media from gallery apps")
                it.createNewFile()
            }
        }
    }

    /**
     * Fetches the list of favorite images by reading the metadata from local storage.
     * Returns a flow of [Response] to handle success or failure scenarios.
     *
     * @return A flow containing a [Response] with either the list of favorite images or an error message.
     */
    override fun getFavorites(): Flow<Response<List<Image>>> = flow {
        try {
            emit(Response.Loading) // Emit loading state
            // Retrieve all JSON files in the liked images directory
            val jsonFiles = likedDir.listFiles { it.extension.lowercase() == "json" } ?: emptyArray()

            Timber.d("getFavorites(): Found ${jsonFiles.size} metadata files in ${likedDir.absolutePath}")

            // Deserialize each JSON file into Image objects
            val images = jsonFiles.mapNotNull { file ->
                runCatching {
                    Gson().fromJson(file.readText(), Image::class.java) // Deserialize JSON to Image object
                }.onFailure {
                    // Log and record any deserialization errors in Firebase Crashlytics
                    Timber.e(it, "Failed to parse JSON in ${file.absolutePath}")
                    FirebaseCrashlytics.getInstance().recordException(it)
                }.getOrNull()
            }

            // Emit the result as a success response
            emit(Response.Success(images))
        } catch (e: Exception) {
            // Log any unexpected errors and record them in Firebase Crashlytics
            Timber.e(e, "Error while fetching favorites")
            FirebaseCrashlytics.getInstance().recordException(e)
            emit(Response.Error(e.localizedMessage ?: "Unknown error occurred"))
        }
    }

    /**
     * Removes an image from the favorites by deleting both the image and its metadata JSON.
     *
     * @param image The [Image] object to be removed from favorites.
     * @return Boolean indicating whether the removal was successful (true) or not (false).
     */
    override suspend fun removeFavorite(image: Image): Boolean {
        val imageFile = File(likedDir, "liked_${image.id}.jpg")  // Image file path
        val jsonFile = File(likedDir, "liked_${image.id}.json")  // Metadata JSON file path

        Timber.d("removeFavorite(): Attempting to delete image and metadata for ${image.id}")

        return try {
            // Attempt to delete the image and its associated JSON file
            val imageDeleted = imageFile.delete()
            val jsonDeleted = jsonFile.delete()

            // Log the result of the deletion attempt
            Timber.d("removeFavorite(${image.id}): Image deleted: $imageDeleted, Metadata deleted: $jsonDeleted")

            // Return true if both files were successfully deleted
            imageDeleted && jsonDeleted
        } catch (e: Exception) {
            // Log and record any errors that occur during the deletion process
            Timber.e(e, "Error deleting favorite image with ID ${image.id}")
            FirebaseCrashlytics.getInstance().recordException(e)
            false
        }
    }
}