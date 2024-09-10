package com.thezayin.domain.usecase

import com.thezayin.domain.repository.HomeCategoryRepository
import com.thezayin.domain.model.CategoriesModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

/**
 * Interface for fetching home categories from the repository.
 *
 * This use case provides access to a flow of category data, encapsulated in a [Response] object.
 */
interface HomeCategories : suspend () -> Flow<Response<List<CategoriesModel>>>

/**
 * Implementation of [HomeCategories] that interacts with the [HomeCategoryRepository] to fetch categories.
 *
 * @property homeCategoryRepository The repository used to fetch home categories.
 */
class HomeCategoriesImpl(
    private val homeCategoryRepository: HomeCategoryRepository
) : HomeCategories {

    /**
     * Retrieves home categories from the category repository.
     *
     * @return A [Flow] emitting [Response] objects, where:
     *         - [Response.Loading] indicates that the category fetching operation is in progress.
     *         - [Response.Success] contains a list of [CategoriesModel] items.
     *         - [Response.Error] contains an error message if the fetch operation fails.
     */
    override suspend fun invoke(): Flow<Response<List<CategoriesModel>>> =
        homeCategoryRepository.getHomeCategories()
}