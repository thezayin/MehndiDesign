package com.thezayin.homes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.thezayin.framework.model.Category
import com.thezayin.framework.model.Image
import com.thezayin.framework.pref.PrefManager
import com.thezayin.framework.remote.SupabaseApiClient
import com.thezayin.framework.utils.Response
import com.thezayin.homes.data.paging.ImagePagingSource
import com.thezayin.homes.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

/**
 * Implementation of [HomeRepository] that handles fetching categories and images.
 * It interacts with [PrefManager] for local data storage and [SupabaseApiClient] for remote data fetching.
 *
 * @param prefManager The manager responsible for local preferences and data storage.
 * @param supabaseApiClient The client used to fetch remote data from Supabase.
 */
class HomeRepositoryImpl(
    private val prefManager: PrefManager,
    private val supabaseApiClient: SupabaseApiClient
) : HomeRepository {

    /**
     * Fetch categories from SharedPreferences (no pagination needed).
     * If categories are not available in preferences, fetch from Supabase and save locally.
     *
     * @return A [Flow] of [Response] containing either a list of [Category] or an error message.
     */
    override suspend fun fetchCategories(): Flow<Response<List<Category>>> = flow {
        try {
            emit(Response.Loading)

            // Fetch categories from SharedPreferences
            val categories = prefManager.getCategories()

            if (categories.isEmpty()) {
                emit(Response.Loading) // Emit loading if categories are empty

                // Fetch categories from Supabase if not found locally
                val fetchedCategories = supabaseApiClient.fetchCategories()
                // Save the fetched categories to SharedPreferences
                prefManager.saveCategories(fetchedCategories)

                emit(Response.Success(fetchedCategories))
            } else {
                // Emit the locally stored categories if available
                emit(Response.Success(categories))
            }
        } catch (e: Exception) {
            // Log and report errors via Firebase Crashlytics
            Timber.tag(TAG).e(e, "Error fetching categories")
            FirebaseCrashlytics.getInstance().recordException(e)

            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    /**
     * Fetch images using Paging3's Pager and [ImagePagingSource] for pagination.
     *
     * @return A [Flow] of [Response] containing a [Flow] of [PagingData] for images or an error message.
     */
    override fun fetchImages(): Flow<Response<Flow<PagingData<Image>>>> = flow {
        try {
            emit(Response.Loading)

            // Set up the Pager with Paging3 configuration
            val pager = Pager(
                config = PagingConfig(pageSize = 10, enablePlaceholders = false),
                pagingSourceFactory = { ImagePagingSource(prefManager) } // Use ImagePagingSource for pagination
            )

            // Emit the flow of images from the Pager
            emit(Response.Success(pager.flow))
        } catch (e: Exception) {
            // Log and report errors via Firebase Crashlytics
            Timber.tag(TAG).e(e, "Error fetching images")
            FirebaseCrashlytics.getInstance().recordException(e)

            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    companion object {
        private const val TAG = "HomeRepositoryImpl"
    }
}