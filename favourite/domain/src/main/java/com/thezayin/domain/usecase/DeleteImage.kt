package com.thezayin.domain.usecase

import arrow.core.Either
import com.thezayin.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow

/**
 * Interface for deleting an image by its ID.
 */
interface DeleteImage : suspend (Int) -> Flow<Either<Exception, Int>>

/**
 * Implementation of [DeleteImage] using [FavouriteRepository].
 *
 * @param favouriteRepository The repository to handle the image deletion logic.
 */
class DeleteImageImpl(private val favouriteRepository: FavouriteRepository) : DeleteImage {
    /**
     * Deletes an image by its ID.
     *
     * @param input The ID of the image to be deleted.
     * @return A [Flow] emitting [Either] containing either the number of rows affected or an [Exception].
     */
    override suspend fun invoke(input: Int): Flow<Either<Exception, Int>> =
        favouriteRepository.removeImage(input)
}
