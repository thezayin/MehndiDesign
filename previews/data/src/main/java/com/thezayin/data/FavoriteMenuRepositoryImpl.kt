package com.thezayin.data

import com.thezayin.domain.model.PreviewMenu
import com.thezayin.domain.repository.FavoriteMenuRepository
import com.thezayin.framework.utils.Response
import com.thezayin.values.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementation of the [FavoriteMenuRepository] interface.
 * Handles the retrieval of favorite menu items.
 */
class FavoriteMenuRepositoryImpl : FavoriteMenuRepository {
    /**
     * Fetches the list of favorite menu items.
     * Emits loading, success, and error states as a [Flow] of [Response].
     *
     * @return A [Flow] that emits [Response] objects with the list of [PreviewMenu].
     */
    override fun getMenuItems(): Flow<Response<List<PreviewMenu>>> = flow {
        try {
            emit(Response.Loading)  // Emit loading state

            // Simulated list of preview menu items
            val menuItems = listOf(
                PreviewMenu(1, "Download", R.drawable.ic_download),
                PreviewMenu(2, "Remove", R.drawable.ic_unlike)
            )

            emit(Response.Success(menuItems))  // Emit success state with the list of menu items
        } catch (e: Exception) {
            // Emit error state with the exception message, or a generic message if null
            emit(
                Response.Error(
                    e.localizedMessage ?: "An error occurred while fetching menu items"
                )
            )
        }
    }
}