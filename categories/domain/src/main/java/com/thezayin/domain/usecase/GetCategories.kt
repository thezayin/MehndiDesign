package com.thezayin.domain.usecase

import com.thezayin.domain.model.CategoryModel
import com.thezayin.domain.repository.CategoryRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetCategories : suspend () -> Flow<Response<List<CategoryModel>>>

class GetCategoriesImpl(private val categoryRepository: CategoryRepository) :
    GetCategories {
    override suspend fun invoke(): Flow<Response<List<CategoryModel>>> =
        categoryRepository.getCategories()
}