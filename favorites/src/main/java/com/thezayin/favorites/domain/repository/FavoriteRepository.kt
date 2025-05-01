package com.thezayin.favorites.domain.repository

import com.thezayin.framework.model.Image
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing the user's favorite images.
 * This interface defines the operations that can be performed on the "liked_images" folder,
 * which is used to store images and their metadata (in JSON format).
 */
interface FavoriteRepository {

    /**
     * Fetches the list of images that the user has "liked".
     * This method reads from the app's private "liked_images" folder where images and their metadata are stored.
     *
     * @return A [Flow] that emits a [Response] containing a list of [Image] objects (the user's liked images).
     *         The response will be either [Response.Loading], [Response.Success] with the images list, or [Response.Error] with an error message.
     */
    fun getFavorites(): Flow<Response<List<Image>>>

    /**
     * Removes an image from the favorites list.
     * This method deletes both the image file (.jpg) and its associated metadata file (.json).
     *
     * @param image The [Image] object representing the image to be removed.
     * @return A [Boolean] indicating whether the image was successfully removed from the favorites (true) or not (false).
     */
    suspend fun removeFavorite(image: Image): Boolean
}
