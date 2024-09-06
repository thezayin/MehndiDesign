package com.thezayin.data.remote

import com.thezayin.domain.repository.CategoryRepository
import com.thezayin.entities.Categories
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class CategoryRepositoryImpl : CategoryRepository {
    override suspend fun getCategories(): Flow<Response<List<Categories>>> = callbackFlow {
        try {
            trySend(Response.Loading)
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
                    icon = com.thezayin.drawable.R.drawable.ic_indian_mehndi,
                    title = "Moroccan Mehndi",
                    isSelected = false
                ),
                Categories(
                    id = 6,
                    icon = com.thezayin.drawable.R.drawable.ic_punjabi_mehndi,
                    title = "Unique Classic Mehndi",
                    isSelected = false
                ),
                Categories(
                    id = 7,
                    icon = com.thezayin.drawable.R.drawable.ic_african_mehndi,
                    title = "Finger Mehndi",
                    isSelected = false
                ),
                Categories(
                    id = 8,
                    icon = com.thezayin.drawable.R.drawable.ic_hena,
                    title = "Foot Mehndi",
                    isSelected = false
                ),
                Categories(
                    id = 9,
                    icon = com.thezayin.drawable.R.drawable.ic_henna,
                    title = "Tikki Mehndi",
                    isSelected = false
                ),
                Categories(
                    id = 10,
                    icon = com.thezayin.drawable.R.drawable.ic_indian_henna,
                    title = "Indo_Western Mehndi",
                    isSelected = false
                ),
                Categories(
                    id = 11,
                    icon = com.thezayin.drawable.R.drawable.ic_bridal_mehndi,
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