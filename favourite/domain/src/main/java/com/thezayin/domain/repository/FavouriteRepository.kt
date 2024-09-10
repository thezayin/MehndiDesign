package com.thezayin.domain.repository

import arrow.core.Either
import com.thezayin.databases.models.LikeImageModel
import kotlinx.coroutines.flow.Flow

/**
 * Interface for managing favorite images in the repository.
 */
interface FavouriteRepository {
    /**
     * Retrieves all favorite images.
     *
     * @return A [Flow] emitting [Either] containing either a list of [LikeImageModel] or an [Exception].
     */
    fun getAllImages(): Flow<Either<Exception, List<LikeImageModel>>>

    /**
     * Inserts a new favorite image.
     *
     * @param url The URL of the image to be added.
     * @return A [Flow] emitting [Either] containing either the ID of the inserted image or an [Exception].
     */
    fun addImage(url: String): Flow<Either<Exception, Long>>

    /**
     * Deletes a favorite image by its ID.
     *
     * @param id The ID of the image to be deleted.
     * @return A [Flow] emitting [Either] containing either the number of rows affected or an [Exception].
     */
    fun removeImage(id: Int): Flow<Either<Exception, Int>>

    /**
     * Checks if an image is already in the favorites list.
     *
     * @param url The URL of the image to check.
     * @return A [Flow] emitting [Either] containing either a boolean indicating existence or an [Exception].
     */
    fun isImageFavorite(url: String): Flow<Either<Exception, Boolean>>
}
