package com.thezayin.like.domain.repository

import com.thezayin.entities.LikeImageModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface LikeRepository {
    fun getAllImages(): Flow<Response<List<LikeImageModel>>>
    fun insertImages( url: String): Flow<Response<Long>>
    fun deleteImage(id: Int): Flow<Response<Int>>
}