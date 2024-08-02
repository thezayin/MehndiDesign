package com.thezayin.like.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.like.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow

interface InsertImage : suspend (String) -> Flow<Response<Long>>

class InsertImageImpl(private val repository: LikeRepository) : InsertImage {
    override suspend fun invoke(input: String): Flow<Response<Long>> =
        repository.insertImages(input)
}