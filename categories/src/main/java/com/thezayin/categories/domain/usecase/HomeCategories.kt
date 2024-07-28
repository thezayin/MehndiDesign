package com.thezayin.categories.domain.usecase

import com.thezayin.categories.domain.repository.HomeCategoryRepository
import com.thezayin.entities.Categories
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface HomeCategories : suspend () -> Flow<Response<List<Categories>>>

class HomeCategoriesImpl(private val homeCategoryRepository: HomeCategoryRepository) :
    HomeCategories {
    override suspend fun invoke(): Flow<Response<List<Categories>>> =
        homeCategoryRepository.getHomeCategories()
}