package com.thezayin.domain.repository

import com.thezayin.domain.model.PreviewMenu
import com.thezayin.framework.utils.Responsee
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for handling the favorite menu operations.
 * Provides access to retrieve the menu items for the favorite menu section.
 */
interface FavoriteMenuRepository {

    /**
     * Retrieves the list of menu items available for the favorite menu.
     * The result is wrapped in a [Flow] of [Responsee], indicating the status of the operation
     * (loading, success, or error) and contains a list of [PreviewMenu].
     *
     * @return A [Flow] that emits [Responsee] objects containing a list of [PreviewMenu].
     */
    fun getMenuItems(): Flow<Responsee<List<PreviewMenu>>>
}