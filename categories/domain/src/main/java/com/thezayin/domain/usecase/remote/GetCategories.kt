package com.thezayin.categories.domain.usecase.remote

import com.thezayin.domain.repository.CategoryRepository
import com.thezayin.entities.Categories
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetCategories : suspend () -> Flow<Response<List<Categories>>>

class GetCategoriesImpl(private val categoryRepository: CategoryRepository) :
    GetCategories {
    override suspend fun invoke(): Flow<Response<List<Categories>>> =
        categoryRepository.getCategories()
}