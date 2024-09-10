package com.thezayin.domain.usecase

import arrow.core.Either
import com.thezayin.domain.repository.FavouriteRepository
import com.thezayin.databases.models.LikeImageModel
import kotlinx.coroutines.flow.Flow

/**
 * Interface for retrieving a list of favorite images.
 *
 * This functional interface takes no input and returns a [Flow] emitting an [Either]
 * containing either a list of [LikeImageModel] or an [Exception].
 */
interface FavouriteImages : suspend () -> Flow<Either<Exception, List<LikeImageModel>>>

/**
 * Implementation of [FavouriteImages] that uses [FavouriteRepository] to retrieve favorite images.
 *
 * @param repository The repository responsible for managing favorite images.
 */
class FavouriteImagesImpl(private val repository: FavouriteRepository) : FavouriteImages {

    /**
     * Retrieves a list of favorite images.
     *
     * @return A [Flow] emitting [Either] containing either a list of [LikeImageModel] or an [Exception].
     */
    override suspend fun invoke(): Flow<Either<Exception, List<LikeImageModel>>> {
        return repository.getAllImages()
    }
}
