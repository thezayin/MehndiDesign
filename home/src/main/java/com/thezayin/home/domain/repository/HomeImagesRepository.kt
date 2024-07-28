package com.thezayin.home.domain.repository

import androidx.paging.PagingData
import com.thezayin.entities.HomeImages
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface HomeImagesRepository {
    fun getAllImages(): Flow<Response<Flow<PagingData<HomeImages>>>>
}