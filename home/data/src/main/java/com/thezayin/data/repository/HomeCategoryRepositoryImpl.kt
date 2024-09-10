package com.thezayin.data.repository

import com.thezayin.domain.repository.HomeCategoryRepository
import com.thezayin.domain.model.CategoriesModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.thezayin.values.R

/**
 * Implementation of the HomeCategoryRepository interface.
 *
 * This repository provides a list of categories for the home screen.
 * It returns data wrapped in a Response object to handle loading, success, and error states.
 */
class HomeCategoryRepositoryImpl : HomeCategoryRepository {

    /**
     * Fetches the list of home categories.
     *
     * @return A Flow emitting a Response object with the list of CategoriesModel.
     */
    override suspend fun getHomeCategories(): Flow<Response<List<CategoriesModel>>> = flow {
        try {
            // Emit loading state while fetching data.
            emit(Response.Loading)

            // Define a static list of categories to be returned.
            val categories = listOf(
                CategoriesModel(
                    id = 1,
                    iconResId = R.drawable.ic_bridal_mehndi,
                    name = "Bridal Mehndi",
                    isSelected = false
                ),
                CategoriesModel(
                    id = 2,
                    iconResId = R.drawable.ic_indian_henna,
                    name = "Indian Mehndi",
                    isSelected = false
                ),
                CategoriesModel(
                    id = 3,
                    iconResId = R.drawable.ic_arabic,
                    name = "Arabic Mehndi",
                    isSelected = false
                ),
                CategoriesModel(
                    id = 4,
                    iconResId = R.drawable.ic_paki,
                    name = "Pakistani Mehndi",
                    isSelected = false
                ),
                CategoriesModel(
                    id = 5,
                    iconResId = R.drawable.ic_tattoo_mehndi,
                    name = "More",
                    isSelected = false
                )
            )

            // Emit the success state with the list of categories.
            emit(Response.Success(categories))
        } catch (exception: Exception) {
            // Emit the error state if an exception occurs.
            emit(Response.Error(exception.localizedMessage ?: "An error occurred"))
        }
    }
}