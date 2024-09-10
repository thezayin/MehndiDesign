package com.thezayin.presentation.event

import com.thezayin.domain.model.CategoryModel

sealed interface CategoryEvent {
    data object ShowLoading : CategoryEvent
    data object HideLoading : CategoryEvent
    data object ShowError : CategoryEvent
    data object HideErrorDialog : CategoryEvent
    data class ErrorMessage(val errorMessage: String) : CategoryEvent
    data class CategoriesList(val result: List<CategoryModel>) : CategoryEvent
}