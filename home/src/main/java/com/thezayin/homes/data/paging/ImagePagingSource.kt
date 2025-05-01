package com.thezayin.homes.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thezayin.framework.model.Image
import com.thezayin.framework.pref.PrefManager
import timber.log.Timber
import com.google.firebase.crashlytics.FirebaseCrashlytics

/**
 * A [PagingSource] implementation to load [Image] objects from local storage (SharedPreferences).
 *
 * @param prefManager The manager responsible for retrieving stored images from SharedPreferences.
 */
class ImagePagingSource(
    private val prefManager: PrefManager
) : PagingSource<Int, Image>() {

    /**
     * Loads a page of images for the given [LoadParams].
     *
     * @param params Parameters for loading, including the page key and requested load size.
     * @return A [LoadResult] containing the loaded images, or an error if the load fails.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        val currentPage = params.key ?: 0  // Default to first page (0) if no key provided
        val limit = params.loadSize       // Number of items to load per page

        Timber.tag(TAG).d("Loading page %d with size %d", currentPage, limit)

        return try {
            // Retrieve the full list of images from SharedPreferences
            val images = prefManager.getImages()
            Timber.tag(TAG).d("Total images available: %d", images.size)

            // Calculate start and end indices for pagination
            val startIndex = currentPage * limit
            val endIndex = (startIndex + limit).coerceAtMost(images.size)

            // Sublist from startIndex to endIndex (exclusive)
            val paginatedImages = if (startIndex < images.size) {
                images.subList(startIndex, endIndex)
            } else {
                emptyList()
            }

            // Determine next and previous keys for paging
            val nextKey = if (paginatedImages.isEmpty()) null else currentPage + 1
            val prevKey = if (currentPage > 0) currentPage - 1 else null

            Timber.tag(TAG).d(
                "Page %d loaded: items %d..%d, nextKey=%s, prevKey=%s",
                currentPage, startIndex, endIndex, nextKey, prevKey
            )

            // Return a successful LoadResult
            LoadResult.Page(
                data = paginatedImages,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            // Log and return any errors encountered during load
            Timber.tag(TAG).e(exception, "Error loading page %d", currentPage)

            // Report the exception to Firebase Crashlytics for monitoring
            FirebaseCrashlytics.getInstance().recordException(exception)

            // Return error result
            LoadResult.Error(exception)
        }
    }

    /**
     * Provides the key for reloading data when invalidation occurs.
     *
     * @param state The current [PagingState] of loaded pages.
     * @return The key of the page to refresh, or null to refresh from scratch.
     */
    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        // Anchor position is the most recently accessed index in the list
        return state.anchorPosition?.let { anchorPosition ->
            // Find the closest page to the anchor position
            val closestPage = state.closestPageToPosition(anchorPosition)
            // Calculate the new key from prevKey or nextKey
            closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val TAG = "ImagePagingSource"
    }
}