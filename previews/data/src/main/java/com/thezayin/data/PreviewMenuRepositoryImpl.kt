package com.thezayin.data

import com.thezayin.domain.model.PreviewMenu
import com.thezayin.domain.repository.PreviewMenuRepository
import com.thezayin.framework.utils.Response
import com.thezayin.values.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementation of the [PreviewMenuRepository] interface.
 * Handles the retrieval of preview menu items.
 */
class PreviewMenuRepositoryImpl : PreviewMenuRepository {

    /**
     * Fetches the list of preview menu items.
     * Emits the success and error states as a [Flow] of [Response].
     *
     * @return A [Flow] that emits [Response] objects containing the list of [PreviewMenu].
     */
    override fun getPreviewMenu(): Flow<Response<List<PreviewMenu>>> = flow {
        try {
            // Simulated list of preview menu items
            val previewMenuItems = listOf(
                PreviewMenu(1, "Download", R.drawable.ic_download),
                PreviewMenu(2, "Like", R.drawable.ic_like)
            )

            // Emit success response with the menu items
            emit(Response.Success(previewMenuItems))
        } catch (e: Exception) {
            // Emit error response with a custom or localized message
            emit(
                Response.Error(
                    e.localizedMessage ?: "An error occurred while fetching preview menu items"
                )
            )
        }
    }
}