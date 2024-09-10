package com.thezayin.data

import com.thezayin.domain.repository.SaveImageRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementation of the [SaveImageRepository] interface.
 * Handles the process of saving an image from a URL to internal storage.
 */
class SaveImageRepositoryImpl : SaveImageRepository {

    /**
     * Saves the image from the provided URL and returns the result as a [Flow] of [Response].
     * The response can represent the loading, success, or error states.
     *
     * @param url The URL of the image to be saved.
     * @return A [Flow] that emits [Response] containing the saved image URI or an error message.
     */
    override fun saveImage(url: String): Flow<Response<String>> = flow {
        try {
            emit(Response.Loading)  // Emit loading state while processing

            // Simulate saving the image to internal storage
            val savedImageUri = saveToInternalStorage(url)

            // Emit success state with the saved image URI
            emit(Response.Success(savedImageUri))
        } catch (e: Exception) {
            // Emit error state with an exception message, or a generic message if null
            emit(
                Response.Error(
                    e.localizedMessage ?: "An error occurred while saving the image"
                )
            )
        }
    }

    /**
     * Saves the image from the URL to internal storage.
     *
     * @param url The URL of the image to be saved.
     * @return The URI of the saved image as a string.
     * @throws Exception if the save operation fails.
     */
    private fun saveToInternalStorage(url: String): String {
        // Here, you would implement the actual logic to save the image to internal storage.
        // This is just a placeholder to simulate the process.

        // Example: You might use Android's File I/O or MediaStore APIs to save the image.
        // For now, let's assume the URL itself is returned as the saved URI.
        return url  // Replace this with the actual saved image URI logic
    }
}