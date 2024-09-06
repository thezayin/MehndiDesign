package com.thezayin.domain.usecase

import arrow.core.Either
import com.thezayin.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import java.io.IOException

interface InsertImage : suspend (String) -> Flow<Either<Exception, Long>>

class InsertImageImpl(private val repository: FavouriteRepository) : InsertImage {
    override suspend fun invoke(input: String): Flow<Either<Exception,Long>> =
        repository.insertImages(input)
}