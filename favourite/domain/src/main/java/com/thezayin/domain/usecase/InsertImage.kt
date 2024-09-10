package com.thezayin.domain.usecase

import arrow.core.Either
import com.thezayin.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow

/**
 * Interface for inserting a new favorite image.
 *
 * This functional interface takes a [String] representing the image URL and returns a [Flow]
 * emitting an [Either] containing either the ID of the inserted image or an [Exception].
 */
interface InsertImage : suspend (String) -> Flow<Either<Exception, Long>>

/**
 * Implementation of [InsertImage] that uses [FavouriteRepository] to insert a new favorite image.
 *
 * @param repository The repository responsible for managing favorite images.
 */
class InsertImageImpl(private val repository: FavouriteRepository) : InsertImage {

    /**
     * Inserts a new favorite image into the repository.
     *
     * @param imageUrl The URL of the image to be inserted.
     * @return A [Flow] emitting [Either] containing either the ID of the inserted image or an [Exception].
     */
    override suspend fun invoke(imageUrl: String): Flow<Either<Exception, Long>> {
        return repository.addImage(imageUrl)
    }
}
