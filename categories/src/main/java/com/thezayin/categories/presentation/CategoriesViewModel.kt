package com.thezayin.categories.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.categories.domain.usecase.GetCategories
import com.thezayin.categories.domain.usecase.HomeCategories
import com.thezayin.entities.GetCategoryState
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetLoadingState
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val getCategories: GetCategories,
    private val getHomeCategories: HomeCategories
) : ViewModel() {

    private val _homeCategories = MutableStateFlow(GetCategoryState())
    val homeCategories = _homeCategories.asStateFlow()

    private val _categories = MutableStateFlow(GetCategoryState())
    val categories = _categories.asStateFlow()

    private val _isQueryError = MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    init {
        fetchCategories()
        fetchHomeCategories()
    }

    private fun fetchHomeCategories() = viewModelScope.launch {
        getHomeCategories().collect { response ->
            when (response) {
                is Response.Success -> {
                    _homeCategories.update { it.copy(list = response.data) }
                }

                else -> {
                    Log.d("CategoriesViewModel", "fetchHomeCategories: ${response}")
                }
            }
        }
    }

    private fun fetchCategories() = viewModelScope.launch {
        getCategories().collect { response ->
            when (response) {
                is Response.Success -> {
                    delay(3000L)
                    _isLoading.update { it.copy(isLoading = false) }
                    _categories.update { it.copy(list = response.data) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }
}