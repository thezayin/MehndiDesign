package com.thezayin.domain.repository

import com.thezayin.domain.model.PreviewMenu
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for handling the favorite menu operations.
 * Provides access to retrieve the menu items for the favorite menu section.
 */
interface FavoriteMenuRepository {

    /**
     * Retrieves the list of menu items available for the favorite menu.
     * The result is wrapped in a [Flow] of [Response], indicating the status of the operation
     * (loading, success, or error) and contains a list of [PreviewMenu].
     *
     * @return A [Flow] that emits [Response] objects containing a list of [PreviewMenu].
     */
    fun getMenuItems(): Flow<Response<List<PreviewMenu>>>
}