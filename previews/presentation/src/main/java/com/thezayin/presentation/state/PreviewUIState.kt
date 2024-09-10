package com.thezayin.presentation.state

import com.thezayin.databases.models.LikeImageModel
import com.thezayin.domain.model.PreviewMenu

/**
 * Data class that holds the UI state for the preview screen.
 *
 * @param isLoading Indicates if the content is currently loading.
 * @param showErrorDialog Indicates if the error dialog should be shown.
 * @param errorMessage Message to be displayed in case of an error.
 * @param isImageInFavorites Indicates if the current image exists in the favorites list.
 * @param imageId The ID of the image being previewed.
 * @param isLikeActionSuccessful Indicates if the action to like or favorite an image was successful.
 * @param favoriteImagesList List of images that are marked as favorites.
 * @param menuItems List of menu options displayed at the bottom of the screen.
 */
data class PreviewUIState(
    val isLoading: Boolean = false,
    val showErrorDialog: Boolean = false,
    val errorMessage: String = "",
    val isImageInFavorites: Boolean = false,
    val imageId: Int? = null,
    val isLikeActionSuccessful: Boolean = false,
    val favoriteImagesList: List<LikeImageModel> = emptyList(),
    val menuItems: List<PreviewMenu> = emptyList(),
)