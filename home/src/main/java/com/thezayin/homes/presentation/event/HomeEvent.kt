package com.thezayin.homes.presentation.event

import androidx.paging.PagingData
import com.thezayin.framework.model.Category
import com.thezayin.framework.model.Image
import kotlinx.coroutines.flow.Flow

sealed interface HomeEvent {
    object ShowLoading : HomeEvent
    object HideLoading : HomeEvent
    object ShowErrorDialog : HomeEvent
    object HideErrorDialog : HomeEvent
    data class ErrorMessage(val errorMessage: String) : HomeEvent
    data class HomeImages(val homeImages: Flow<PagingData<Image>>) : HomeEvent
    data class HomeCategories(val categories: List<Category>) : HomeEvent
    data class SelectImage(val image: Image) : HomeEvent
    data class SelectCategory(val category: Category) : HomeEvent
    data class ShowBannerAd(val show: Boolean) : HomeEvent
    data class ShowImageSelectionAd(val show: Boolean) : HomeEvent
    data class ShowCategorySelectionAd(val show: Boolean) : HomeEvent
    data class ShowMyLikeClickAd(val show: Boolean) : HomeEvent
}