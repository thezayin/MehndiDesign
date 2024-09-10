package com.thezayin.presentation.state

import androidx.paging.PagingData
import com.thezayin.framework.state.State
import kotlinx.coroutines.flow.Flow

sealed interface CategoryImageState : State {
    data class CategoryImageUiState(
        val isLoading: Boolean = false,
        val images: Flow<PagingData<String>>? = null,
        val showError: Boolean = false,
        val errorMessage: String = "",
    )
}