package com.thezayin.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thezayin.databases.models.ImageModel
import kotlinx.coroutines.delay

/**
 * PagingSource for loading images from the HomeImageDao.
 *
 * This PagingSource handles pagination by loading images in chunks from the database.
 * It manages page indices and handles the logic to load more data or indicate
 * the end of the dataset.
 *
 * @property dao The data access object for fetching images.
 */
class HomeImagePagingSource(
    private val dao: com.thezayin.databases.dao.HomeImageDao
) : PagingSource<Int, ImageModel>() {

    /**
     * Loads a page of image data from the data source.
     *
     * @param params Parameters for loading data, including the requested page and load size.
     * @return A LoadResult containing the loaded data and pagination information.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageModel> {
        // Determine the current page. Default to 0 if not provided.
        val currentPage = params.key ?: 0

        return try {
            // Fetch images from the data source.
            val images = dao.getAllImages(params.loadSize, currentPage * params.loadSize)

            // Simulate network delay for pagination purposes.
            if (currentPage != 0) delay(1000)

            // Return the result with pagination information.
            LoadResult.Page(
                data = images,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (images.isEmpty()) null else currentPage + 1
            )
        } catch (exception: Exception) {
            // Handle any errors that occur during data loading.
            LoadResult.Error(exception)
        }
    }

    /**
     * Provides the key for refreshing the data.
     *
     * @param state The current paging state containing information about the loaded pages.
     * @return The key for the page to be used for refreshing, or null if not applicable.
     */
    override fun getRefreshKey(state: PagingState<Int, ImageModel>): Int? {
        // Use the anchor position to determine the page to refresh.
        return state.anchorPosition?.let { anchorPosition ->
            val closestPage = state.closestPageToPosition(anchorPosition)
            closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
        }
    }
}