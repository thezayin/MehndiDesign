package com.thezayin.like.domain.usecase

import androidx.paging.PagingData
import com.thezayin.entities.LikeImageModel
import com.thezayin.framework.utils.Response
import com.thezayin.like.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow

interface LikedImages : suspend () -> Flow<Response<List<LikeImageModel>>>

class LikedImagesImpl(private val repository: LikeRepository) : LikedImages {
    override suspend fun invoke(): Flow<Response<List<LikeImageModel>>> =
        repository.getAllImages()
}