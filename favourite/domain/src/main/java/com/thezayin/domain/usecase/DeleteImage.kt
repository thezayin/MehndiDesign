package com.thezayin.domain.usecase

import arrow.core.Either
import com.thezayin.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import java.io.IOException

interface DeleteImage : suspend (Int) -> Flow<Either<Exception,Int>>

class DeleteImageImpl(private val favouriteRepository: FavouriteRepository) : DeleteImage {
    override suspend fun invoke(input: Int): Flow<Either<Exception,Int>> =
        favouriteRepository.deleteImage(input)
}