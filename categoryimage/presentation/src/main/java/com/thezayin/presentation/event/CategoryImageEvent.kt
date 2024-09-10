package com.thezayin.presentation.event

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

sealed interface CategoryImageEvent {
    data object ShowLoading : CategoryImageEvent
    data object HideLoading : CategoryImageEvent
    data object ShowError : CategoryImageEvent
    data object HideErrorDialog : CategoryImageEvent
    data class ErrorMessage(val errorMessage: String) : CategoryImageEvent
    data class ImagesList(val result: Flow<PagingData<String>>) : CategoryImageEvent
}