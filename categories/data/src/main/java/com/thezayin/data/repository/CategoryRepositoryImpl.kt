package com.thezayin.data.repository

import com.thezayin.domain.model.CategoryModel
import com.thezayin.domain.repository.CategoryRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.thezayin.values.R

class CategoryRepositoryImpl : CategoryRepository {
    override suspend fun getCategories(): Flow<Response<List<CategoryModel>>> = callbackFlow {
        try {
            trySend(Response.Loading)
            val list = listOf(
                CategoryModel(
                    id = 1,
                    icon = R.drawable.ic_bridal_mehndi,
                    title = "Bridal Mehndi",
                    isSelected = false
                ),
                CategoryModel(
                    id = 2,
                    icon = R.drawable.ic_indian_henna,
                    title = "Indian Mehndi",
                    isSelected = false
                ),
                CategoryModel(
                    id = 3,
                    icon = R.drawable.ic_arabic,
                    title = "Arabic Mehndi",
                    isSelected = false
                ),
                CategoryModel(
                    id = 4,
                    icon = R.drawable.ic_paki,
                    title = "Pakistani Mehndi",
                    isSelected = false
                ),
                CategoryModel(
                    id = 5,
                    icon = R.drawable.ic_indian_mehndi,
                    title = "Moroccan Mehndi",
                    isSelected = false
                ),
                CategoryModel(
                    id = 6,
                    icon = R.drawable.ic_punjabi_mehndi,
                    title = "Unique Classic Mehndi",
                    isSelected = false
                ),
                CategoryModel(
                    id = 7,
                    icon = R.drawable.ic_african_mehndi,
                    title = "Finger Mehndi",
                    isSelected = false
                ),
                CategoryModel(
                    id = 8,
                    icon = R.drawable.ic_hena,
                    title = "Foot Mehndi",
                    isSelected = false
                ),
                CategoryModel(
                    id = 9,
                    icon = R.drawable.ic_henna,
                    title = "Tikki Mehndi",
                    isSelected = false
                ),
                CategoryModel(
                    id = 10,
                    icon = R.drawable.ic_indian_henna,
                    title = "Indo_Western Mehndi",
                    isSelected = false
                ),
                CategoryModel(
                    id = 11,
                    icon = R.drawable.ic_bridal_mehndi,
                    title = "Tattoo Mehndi",
                    isSelected = false
                ),
            )
            trySend(Response.Success(list))
            awaitClose { cancel() }
        } catch (e: Exception) {
            trySend(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}