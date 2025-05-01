package com.thezayin.domain.repository

import com.thezayin.domain.model.PreviewMenu
import com.thezayin.framework.utils.Responsee
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for handling the preview menu operations.
 * Provides access to retrieve the menu items for the preview section.
 */
interface PreviewMenuRepository {

    /**
     * Retrieves the list of menu items available for the preview section.
     * The result is wrapped in a [Flow] of [Responsee], indicating the status of the operation
     * (loading, success, or error) and contains a list of [PreviewMenu].
     *
     * @return A [Flow] that emits [Responsee] objects containing a list of [PreviewMenu].
     */
    fun getPreviewMenu(): Flow<Responsee<List<PreviewMenu>>>
}