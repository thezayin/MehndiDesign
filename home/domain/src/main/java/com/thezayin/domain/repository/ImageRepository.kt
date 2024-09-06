package com.thezayin.domain.repository

import androidx.paging.PagingData
import com.thezayin.entities.ImageModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun getAllImages(): Flow<Response<Flow<PagingData<ImageModel>>>>
}