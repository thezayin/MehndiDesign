package com.thezayin.like.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetLoadingState
import com.thezayin.entities.GetPreviewItemState
import com.thezayin.entities.GetSuccessState
import com.thezayin.entities.LikeImageModel
import com.thezayin.framework.extension.functions.saveToInternalStorage
import com.thezayin.framework.utils.Response
import com.thezayin.like.domain.usecase.DeleteImage
import com.thezayin.like.domain.usecase.InsertImage
import com.thezayin.like.domain.usecase.LikeMenuList
import com.thezayin.like.domain.usecase.LikedImages
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LikeViewModel(
    private val deleteImage: DeleteImage,
    private val insertImage: InsertImage,
    private val likedImages: LikedImages,
    private val menuList: LikeMenuList
) : ViewModel() {
    private val _imagesUrl = MutableStateFlow(ImagesUrl())
    val imagesUrl = _imagesUrl.asStateFlow()

    private val _bottomMenuList = MutableStateFlow(GetPreviewItemState())
    val bottomMenuList = _bottomMenuList.asStateFlow()

    private val _isLikeSuccess = MutableStateFlow(GetSuccessState())
    val isLikeSuccess = _isLikeSuccess.asStateFlow()

    private val _isQueryError = MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    init {
        fetchAllImages()
        fetchBottomMenu()
    }

    private fun fetchBottomMenu() = viewModelScope.launch {
        menuList().collect { response ->
            when (response) {
                is Response.Success -> {
                    _bottomMenuList.update {
                        it.copy(list = response.data)
                    }
                    delay(2000L)
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
                }

                is Response.Loading -> {
                    _isLoading.update {
                        it.copy(isLoading = true)
                    }
                }

                is Response.Error -> {
                    _isLoading.update {
                        it.copy(isLoading = false)
                    }
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

    private fun fetchAllImages() = viewModelScope.launch {
        likedImages().collect { response ->
            when (response) {
                is Response.Success -> {
                    _imagesUrl.update {
                        it.copy(list = response.data)
                    }
                    delay(2000L)
                    _isLoading.update { it.copy(isLoading = false) }
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

    fun removeImage(id: Int) = viewModelScope.launch {
        deleteImage(id).collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchAllImages()
                    _isLikeSuccess.update { it.copy(isSuccess = true) }
                    delay(2000L)
                    _isLoading.update { it.copy(isLoading = false) }
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

    fun addImage(url: String) = viewModelScope.launch {
        imagesUrl.value.list.forEach {
            if (it.URL == url) {
                return@launch
            }
        }
        insertImage(url).collect {
            when (it) {
                is Response.Success -> {
                    fetchAllImages()
                    _isLikeSuccess.update { it.copy(isSuccess = true) }
                    delay(2000L)
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = it.e
                        )
                    }
                }
            }
        }
    }

    data class ImagesUrl(
        val list: List<LikeImageModel> = emptyList()
    )
}