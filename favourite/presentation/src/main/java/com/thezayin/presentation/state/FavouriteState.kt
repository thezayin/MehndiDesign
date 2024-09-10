package com.thezayin.presentation.state

import com.thezayin.databases.models.LikeImageModel

/**
 * Data class that holds the UI state for the favorites screen.
 *
 * @param isLoading Indicates if the content is currently loading. Defaults to `true`.
 * @param errorDialog Indicates if the error dialog should be shown. Defaults to `false`.
 * @param errorMessage Message to be displayed in case of an error. Defaults to an empty string.
 * @param list List of images that are marked as favorites. Defaults to an empty list.
 */
data class FavouriteState(
    val isLoading: Boolean = true,
    val errorDialog: Boolean = false,
    val errorMessage: String = "",
    val list: List<LikeImageModel> = emptyList(),
)