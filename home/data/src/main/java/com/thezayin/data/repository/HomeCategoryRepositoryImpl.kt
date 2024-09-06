package com.thezayin.data.repository

import com.thezayin.domain.repository.HomeCategoryRepository
import com.thezayin.entities.Categories
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeCategoryRepositoryImpl : HomeCategoryRepository {
    override suspend fun getHomeCategories(): Flow<Response<List<Categories>>> = flow {
        try {
            emit(Response.Loading)
            val list = listOf(
                Categories(
                    id = 1,
                    icon = com.thezayin.drawable.R.drawable.ic_bridal_mehndi,
                    title = "Bridal Mehndi",
                    isSelected = false
                ),
                Categories(
                    id = 2,
                    icon = com.thezayin.drawable.R.drawable.ic_indian_henna,
                    title = "Indian Mehndi",
                    isSelected = false
                ),
                Categories(
                    id = 3,
                    icon = com.thezayin.drawable.R.drawable.ic_arabic,
                    title = "Arabic Mehndi",
                    isSelected = false
                ),
                Categories(
                    id = 4,
                    icon = com.thezayin.drawable.R.drawable.ic_paki,
                    title = "Pakistani Mehndi",
                    isSelected = false
                ),
                Categories(
                    id = 5,
                    icon = com.thezayin.drawable.R.drawable.ic_tattoo_mehndi,
                    title = "More",
                    isSelected = false
                )
            )
            emit(Response.Success(list))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}