package com.thezayin.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetHomeImageState
import com.thezayin.entities.GetLoadingState
import com.thezayin.framework.utils.Response
import com.thezayin.home.domain.usecase.GetHomeImages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val getHomeImages: GetHomeImages) : ViewModel() {

    private val _homeImages = MutableStateFlow(GetHomeImageState())
    val homeImages = _homeImages.asStateFlow()

    private val _isQueryError = MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    init {
        fetchHomeImages()
    }

    private fun fetchHomeImages() = viewModelScope.launch {
        getHomeImages().collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _homeImages.update { it.copy(list = response.data) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}