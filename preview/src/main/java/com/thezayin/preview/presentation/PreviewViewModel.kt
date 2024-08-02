package com.thezayin.preview.presentation

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetLoadingState
import com.thezayin.entities.GetPreviewItemState
import com.thezayin.entities.GetSuccessState
import com.thezayin.framework.utils.Response
import com.thezayin.preview.domain.usecase.PreviewMenuItems
import com.thezayin.preview.domain.usecase.SaveImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PreviewViewModel(private val saveImage: SaveImage, private val menuItems: PreviewMenuItems) :
    ViewModel() {

    private val _isQuerySuccessful = MutableStateFlow(GetSuccessState())
    val isQuerySuccess = _isQuerySuccessful.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow(GetErrorState())
    val isError = _isError.asStateFlow()

    private val _getMenuItems = MutableStateFlow(GetPreviewItemState())
    val getMenuItems = _getMenuItems.asStateFlow()

    init {
        getMenuItems()
    }

    private fun getMenuItems() = viewModelScope.launch {
        menuItems().collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _getMenuItems.update { it.copy(list = response.data) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun saveImageToStorage(url: String) = viewModelScope.launch {
        saveImage(url).collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQuerySuccessful.update { it.copy(isSuccess = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isError.update { it.copy(isError = true, errorMessage = response.e) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}