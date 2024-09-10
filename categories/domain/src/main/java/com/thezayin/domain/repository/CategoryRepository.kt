package com.thezayin.domain.repository

import com.thezayin.domain.model.CategoryModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<Response<List<CategoryModel>>>

}