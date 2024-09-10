package com.thezayin.presentation.event

import androidx.paging.PagingData
import com.thezayin.databases.models.ImageModel
import com.thezayin.domain.model.CategoriesModel
import kotlinx.coroutines.flow.Flow

/**
 * Sealed interface representing various events for the Home screen.
 */
sealed interface HomeEvents {

    /**
     * Event to indicate that loading should be shown.
     */
    data object ShowLoading : HomeEvents

    /**
     * Event to indicate that loading should be hidden.
     */
    data object HideLoading : HomeEvents

    /**
     * Event to show an error dialog.
     */
    data object ShowErrorDialog : HomeEvents

    /**
     * Event to hide the error dialog.
     */
    data object HideErrorDialog : HomeEvents

    /**
     * Event containing an error message to be displayed.
     *
     * @property errorMessage The error message to display.
     */
    data class ErrorMessage(val errorMessage: String) : HomeEvents

    /**
     * Event containing a flow of paginated image data.
     *
     * @property homeImages A flow of paginated image data for the Home screen.
     */
    data class HomeImages(val homeImages: Flow<PagingData<ImageModel>>) : HomeEvents

    /**
     * Event containing a list of home categories.
     *
     * @property categories A list of categories to be displayed on the Home screen.
     */
    data class HomeCategories(val categories: List<CategoriesModel>) : HomeEvents
}
