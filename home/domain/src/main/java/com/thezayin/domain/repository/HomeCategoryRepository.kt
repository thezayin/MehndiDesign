package com.thezayin.domain.repository

import com.thezayin.entities.Categories
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface HomeCategoryRepository {
    suspend fun getHomeCategories(): Flow<Response<List<Categories>>>
}