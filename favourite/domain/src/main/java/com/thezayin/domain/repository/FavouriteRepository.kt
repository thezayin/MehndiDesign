package com.thezayin.domain.repository

import arrow.core.Either
import com.thezayin.entities.LikeImageModel
import kotlinx.coroutines.flow.Flow
import java.io.IOException

interface FavouriteRepository {
    fun getAllImages(): Flow<Either<Exception, List<LikeImageModel>>>
    fun insertImages(url: String): Flow<Either<Exception, Long>>
    fun deleteImage(id: Int): Flow<Either<Exception, Int>>
    fun checkImage(url: String): Flow<Either<Exception, Boolean>>
}