package com.thezayin.data.repository

import arrow.core.Either
import com.thezayin.databases.dao.FavouriteDao
import com.thezayin.databases.models.LikeImageModel
import com.thezayin.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementation of [FavouriteRepository] for managing favorite images using [FavouriteDao].
 *
 * @param dao The Data Access Object for interacting with the favorite images database.
 */
class FavouriteRepositoryImpl(private val dao: FavouriteDao) : FavouriteRepository {

    /**
     * Retrieves all favorite images from the database.
     *
     * @return A [Flow] emitting [Either] containing either a list of [LikeImageModel] or an [Exception].
     */
    override fun getAllImages(): Flow<Either<Exception, List<LikeImageModel>>> = flow {
        try {
            // Fetch all favorite images from the DAO
            val images = dao.getAllImages()
            emit(Either.Right(images))
        } catch (e: Exception) {
            // Emit an error if an exception occurs
            emit(Either.Left(e))
        }
    }

    /**
     * Adds a new image to the favorites list.
     *
     * @param url The URL of the image to be added.
     * @return A [Flow] emitting [Either] containing either the ID of the inserted image or an [Exception].
     */
    override fun addImage(url: String): Flow<Either<Exception, Long>> = flow {
        try {
            // Insert the new image into the DAO
            val id = dao.insertImages(LikeImageModel(URL = url))
            emit(Either.Right(id))
        } catch (e: Exception) {
            // Emit an error if an exception occurs
            emit(Either.Left(e))
        }
    }

    /**
     * Removes an image from the favorites list by its ID.
     *
     * @param id The ID of the image to be deleted.
     * @return A [Flow] emitting [Either] containing either the number of rows affected or an [Exception].
     */
    override fun removeImage(id: Int): Flow<Either<Exception, Int>> = flow {
        try {
            // Delete the image from the DAO
            val affectedRows = dao.deleteImage(id)
            emit(Either.Right(affectedRows))
        } catch (e: Exception) {
            // Emit an error if an exception occurs
            emit(Either.Left(e))
        }
    }

    /**
     * Checks if an image is already in the favorites list.
     *
     * @param url The URL of the image to check.
     * @return A [Flow] emitting [Either] containing either a boolean indicating existence or an [Exception].
     */
    override fun isImageFavorite(url: String): Flow<Either<Exception, Boolean>> = flow {
        try {
            // Check if the image exists in the DAO
            val exists = dao.checkImage(url)
            emit(Either.Right(exists))
        } catch (e: Exception) {
            // Emit an error if an exception occurs
            emit(Either.Left(e))
        }
    }
}
