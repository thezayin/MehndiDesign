package com.thezayin.domain.usecase

import arrow.core.Either
import com.thezayin.domain.repository.FavouriteRepository
import com.thezayin.entities.LikeImageModel
import kotlinx.coroutines.flow.Flow

interface FavouriteImages : suspend () -> Flow<Either<Exception, List<LikeImageModel>>>

class FavouriteImagesImpl(private val repository: FavouriteRepository) : FavouriteImages {
    override suspend fun invoke(): Flow<Either<Exception, List<LikeImageModel>>> =
        repository.getAllImages()
}