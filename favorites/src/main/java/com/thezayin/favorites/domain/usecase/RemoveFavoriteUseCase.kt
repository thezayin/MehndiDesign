package com.thezayin.favorites.domain.usecase

import com.thezayin.framework.model.Image
import com.thezayin.favorites.domain.repository.FavoriteRepository

/**
 * Use case for removing an image from the user's favorites.
 *
 * This use case interacts with the [FavoriteRepository] to remove a specific image from
 * the "liked_images" folder (both the image and its metadata). It returns a boolean
 * indicating whether the operation was successful.
 */
fun interface RemoveFavoriteUseCase {

    /**
     * Removes an image from the user's list of favorites.
     * This method will call the repository to delete the image file and its associated metadata.
     *
     * @param image The [Image] to be removed from the favorites.
     * @return A boolean indicating whether the deletion was successful.
     */
    suspend operator fun invoke(image: Image): Boolean
}

/**
 * Implementation of [RemoveFavoriteUseCase] that removes an image from the repository.
 * This class interacts with the [FavoriteRepository] to delete the image and its metadata.
 */
class RemoveFavoriteUseCaseImpl(
    private val repo: FavoriteRepository
) : RemoveFavoriteUseCase {

    /**
     * Removes the specified image from the user's favorites.
     * This method delegates the operation to the [FavoriteRepository] to perform the actual removal.
     *
     * @param image The [Image] to be removed from the favorites.
     * @return A boolean indicating whether the removal was successful.
     */
    override suspend fun invoke(image: Image): Boolean =
        repo.removeFavorite(image)
}