package com.thezayin.domain.usecase

import com.thezayin.domain.repository.HomeCategoryRepository
import com.thezayin.entities.Categories
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface HomeCategories : suspend () -> Flow<Response<List<Categories>>>

class HomeCategoriesImpl(private val homeCategoryRepository: HomeCategoryRepository) :
    HomeCategories {
    override suspend fun invoke(): Flow<Response<List<Categories>>> =
        homeCategoryRepository.getHomeCategories()
}