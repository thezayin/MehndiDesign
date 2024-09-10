package com.thezayin.presentation.state

import androidx.paging.PagingData
import com.thezayin.databases.models.ImageModel
import com.thezayin.domain.model.CategoriesModel
import kotlinx.coroutines.flow.Flow

/**
 * Data class representing the UI state for the Home screen.
 *
 * @property isLoading Indicates whether data is currently being loaded.
 * @property homeImages A flow of paginated image data. This represents the current images being displayed.
 * @property homeCategories A list of categories available on the Home screen.
 * @property errorDialog A flag indicating whether an error dialog should be displayed.
 * @property errorMessage A message to be shown in case of an error.
 */
data class HomeState(
    val isLoading: Boolean = false,
    val homeImages: Flow<PagingData<ImageModel>>? = null,
    val homeCategories: List<CategoriesModel>? = null,
    val errorDialog: Boolean = false,
    val errorMessage: String = ""
)