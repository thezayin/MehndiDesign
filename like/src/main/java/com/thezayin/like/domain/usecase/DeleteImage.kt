package com.thezayin.like.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.like.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow

interface DeleteImage : suspend (Int) -> Flow<Response<Int>>

class DeleteImageImpl(private val likeRepository: LikeRepository) : DeleteImage {
    override suspend fun invoke(input: Int): Flow<Response<Int>> =
        likeRepository.deleteImage(input)
}