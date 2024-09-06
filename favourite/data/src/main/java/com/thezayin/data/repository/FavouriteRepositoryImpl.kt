package com.thezayin.data.repository

import arrow.core.Either
import com.thezayin.data.dao.FavouriteDao
import com.thezayin.domain.repository.FavouriteRepository
import com.thezayin.entities.LikeImageModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavouriteRepositoryImpl(private val dao: FavouriteDao) : FavouriteRepository {
    override fun getAllImages(): Flow<Either<Exception, List<LikeImageModel>>> = flow {
        try {
            val list = dao.getAllImages()
            emit(Either.Right(list))
        } catch (e: Exception) {
            emit(Either.Left(e))
        }
    }

    override fun insertImages(url: String): Flow<Either<Exception, Long>> = flow {
        try {
            val row = dao.insertImages(
                LikeImageModel(
                    URL = url
                )
            )

            emit(Either.Right(row))
        } catch (e: Exception) {
            emit(Either.Left(e))
        }
    }

    override fun deleteImage(id: Int): Flow<Either<Exception, Int>> = flow {
        try {
            val row = dao.deleteImage(id)
            emit(Either.Right(row))
        } catch (e: Exception) {
            Either.Left(e)
        }
    }

    override fun checkImage(url: String): Flow<Either<Exception, Boolean>> = flow {
        try {
            val isExist = dao.checkImage(url)
          emit(Either.Right(isExist))
        } catch (e: Exception) {
            emit(Either.Left(e))
        }
    }
}