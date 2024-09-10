package com.thezayin.presentation.events

import com.thezayin.databases.models.LikeImageModel

/**
 * Sealed interface defining events related to the Favourite feature.
 */
sealed interface FavouriteEvents {

    /**
     * Event indicating that loading should be hidden.
     */
    data object HideLoading : FavouriteEvents

    /**
     * Event indicating that an error dialog should be shown.
     */
    data object ShowErrorDialog : FavouriteEvents

    /**
     * Event containing an error message to be displayed.
     *
     * @property errorMessage The error message to be shown.
     */
    data class ErrorMessage(val errorMessage: String) : FavouriteEvents

    /**
     * Event containing a list of images.
     *
     * @property list The list of images to be displayed.
     */
    data class GetImages(val list: List<LikeImageModel>) : FavouriteEvents
}
