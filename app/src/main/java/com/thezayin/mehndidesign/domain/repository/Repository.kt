package com.thezayin.mehndidesign.domain.repository

import androidx.paging.PagingData
import com.thezayin.mehndidesign.domain.model.UnsplashImage
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAllImages(): Flow<PagingData<UnsplashImage>>
    fun searchImages(query: String): Flow<PagingData<UnsplashImage>>
}