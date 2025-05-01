package com.thezayin.homes.domain.repository

import androidx.paging.PagingData
import com.thezayin.framework.model.Category
import com.thezayin.framework.model.Image
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

/**
 * Interface for the HomeRepository, responsible for data operations related to the home screen.
 * This repository serves as the contract that defines the methods for fetching categories and images.
 * The actual implementation of this repository will handle fetching data from local or remote sources.
 *
 * The repository ensures that the data-fetching logic is abstracted and provides a clean interface
 * to interact with the data, whether the source is a local cache or a remote server.
 */
interface HomeRepository {

    /**
     * Fetches a list of categories.
     * This method is responsible for retrieving categories either from a local data store (e.g., SharedPreferences)
     * or from a remote source (e.g., Supabase API). If the categories are not available locally,
     * it will attempt to fetch them from the remote source and save them for future use.
     *
     * @return A [Flow] of [Response] containing a list of [Category] objects or an error message.
     *         The [Response] will emit [Response.Loading] while the request is in progress,
     *         [Response.Success] once the data is successfully retrieved, or [Response.Error] if an error occurs.
     */
    suspend fun fetchCategories(): Flow<Response<List<Category>>>

    /**
     * Fetches a paginated list of images.
     * This method utilizes Paging3's Pager] to fetch images in a paginated fashion. It ensures that only a specific
     * number of images are loaded at a time (page size) and handles paging through the data using [PagingData].
     * The images are retrieved using the ImagePagingSource], which is responsible for fetching data from the local data store.
     *
     * @return A [Flow] of [Response] containing a [Flow] of [PagingData] for images.
     *         The [PagingData] is a paginated list of [Image] objects. [Response.Loading] is emitted when data is being fetched,
     *         [Response.Success] when data is successfully loaded, and [Response.Error] if any error occurs during the data fetch.
     */
    fun fetchImages(): Flow<Response<Flow<PagingData<Image>>>>
}
