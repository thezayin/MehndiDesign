package com.thezayin.domain.repository

import com.thezayin.domain.model.CategoriesModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
/**
 * Interface for fetching home category data from a repository.
 */
interface HomeCategoryRepository {

    /**
     * Fetches a list of home categories.
     *
     * @return A [Flow] emitting [Response] objects that contain a list of [CategoriesModel] objects.
     *         The [Response] can be in one of the following states:
     *         - [Response.Loading]: Indicates that the data is currently being loaded.
     *         - [Response.Success]: Contains the list of [Category] objects if the operation succeeds.
     *         - [Response.Error]: Contains an error message if the operation fails.
     */
    suspend fun getHomeCategories(): Flow<Response<List<CategoriesModel>>>
}