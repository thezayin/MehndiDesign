package com.thezayin.presentation.state

import com.thezayin.domain.model.CategoryModel
import com.thezayin.framework.state.State

sealed interface CategoryState : State {
    data class CategoryListUiState(
        val isLoading: Boolean = false,
        val categories: List<CategoryModel> = emptyList(),
        val showError: Boolean = false,
        val errorMessage: String = "",
    )
}