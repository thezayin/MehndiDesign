package com.thezayin.domain.repository

import com.thezayin.entities.Categories
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<Response<List<Categories>>>

}