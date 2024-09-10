package com.thezayin.presentation.event

import com.thezayin.databases.models.LikeImageModel
import com.thezayin.domain.model.PreviewMenu

/**
 * Sealed interface representing different UI events for the preview screen.
 */
sealed interface PreviewEvents {
    /** Event to indicate that loading should be shown. */
    data object ShowLoading : PreviewEvents

    /** Event to indicate that loading should be hidden. */
    data object HideLoading : PreviewEvents

    /** Event to show the error dialog. */
    data object ShowErrorDialog : PreviewEvents

    /** Event to hide the error dialog. */
    data object HideErrorDialog : PreviewEvents

    /**
     * Event to display an error message.
     * @param errorMessage The message to be shown.
     */
    data class DisplayErrorMessage(val errorMessage: String) : PreviewEvents

    /**
     * Event to check if the image exists in the favorites list.
     * @param isSuccess Boolean indicating the result of the check.
     */
    data class ImageExistsInFavorites(val isSuccess: Boolean) : PreviewEvents

    /**
     * Event to retrieve the list of favorite images.
     * @param favoriteImages List of images marked as favorite.
     */
    data class RetrieveFavoriteImages(val favoriteImages: List<LikeImageModel>) : PreviewEvents

    /**
     * Event to retrieve the bottom menu options.
     * @param menuItems List of menu items for the bottom menu.
     */
    data class RetrieveBottomMenuItems(val menuItems: List<PreviewMenu>) : PreviewEvents

    /**
     * Event to indicate the success of a "like" action.
     * @param isSuccess Boolean indicating whether the like action was successful.
     */
    data class LikeActionSuccess(val isSuccess: Boolean) : PreviewEvents

    /**
     * Event to retrieve the ID of the image.
     * @param imageId The ID of the current image.
     */
    data class RetrieveImageId(val imageId: Int) : PreviewEvents
}