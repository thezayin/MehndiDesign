package com.thezayin.homes.domain.usecase

import com.thezayin.framework.model.Category
import com.thezayin.framework.utils.Response
import com.thezayin.homes.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for fetching a list of [Category] objects for the home screen.
 *
 * This defines the contract for retrieving categories, encapsulating the execution
 * logic and allowing callers to remain agnostic of where or how the data is fetched.
 *
 * @return A [Flow] emitting [Response.Loading] while fetching,
 *         [Response.Success] with the list of categories on success,
 *         or [Response.Error] if an error occurs.
 */
interface GetCategoriesUseCase : suspend () -> Flow<Response<List<Category>>>

/**
 * Default implementation of [GetCategoriesUseCase].
 *
 * Delegates the actual data retrieval to [HomeRepository], so it can
 * fetch categories from local storage or remote as needed.
 *
 * @param homeRepository The repository responsible for fetching category data.
 */
class GetCategoriesUseCaseImpl(
    private val homeRepository: HomeRepository
) : GetCategoriesUseCase {

    /**
     * Executes the use case.
     *
     * Simply forwards the call to [HomeRepository.fetchCategories], returning
     * its result flow of [Response] wrapping a list of [Category].
     */
    override suspend fun invoke(): Flow<Response<List<Category>>> =
        homeRepository.fetchCategories()
}
