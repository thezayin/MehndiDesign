package com.thezayin.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.ads.GoogleManager
import com.thezayin.domain.usecase.DeleteImage
import com.thezayin.domain.usecase.FavouriteImages
import com.thezayin.domain.usecase.FavouriteMenuList
import com.thezayin.domain.usecase.InsertImage
import com.thezayin.domain.usecase.PreviewItems
import com.thezayin.domain.usecase.SaveImage
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetIdState
import com.thezayin.entities.GetLoadingState
import com.thezayin.entities.GetPreviewItemState
import com.thezayin.entities.GetSuccessState
import com.thezayin.entities.LikeImageModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PreviewViewModel(
    private val googleManager: GoogleManager,
    private val saveImage: SaveImage,
    private val menuItems: PreviewItems,
    private val deleteImage: DeleteImage,
    private val insertImage: InsertImage,
    private val favouriteImages: FavouriteImages,
    private val favouriteMenuList: FavouriteMenuList
) :
    ViewModel() {
    private val _isImageExist = MutableStateFlow(GetSuccessState())
    val isImageExist = _isImageExist.asStateFlow()

    private val _urlId = MutableStateFlow(GetIdState())
    val urlId = _urlId.asStateFlow()

    private val _imagesUrl = MutableStateFlow(ImagesUrl())
    val imagesUrl = _imagesUrl.asStateFlow()

    private val _bottomMenuList = MutableStateFlow(GetPreviewItemState())
    val bottomMenuList = _bottomMenuList.asStateFlow()

    private val _isQuerySuccessful = MutableStateFlow(GetSuccessState())
    val isQuerySuccess = _isQuerySuccessful.asStateFlow()

    private val _isQueryError = MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow(GetErrorState())
    val isError = _isError.asStateFlow()

    private val _getMenuItems = MutableStateFlow(GetPreviewItemState())
    val getMenuItems = _getMenuItems.asStateFlow()

    private val _getFavouriteMenuItems = MutableStateFlow(GetPreviewItemState())
    val getFavouriteMenuItems = _getFavouriteMenuItems.asStateFlow()

    private val _isLikeSuccess = MutableStateFlow(GetSuccessState())
    val isLikeSuccess = _isLikeSuccess.asStateFlow()


    var nativeAd = mutableStateOf<NativeAd?>(null)
        private set

    init {
        getMenuItems()
        getFavouriteItems()
        fetchAllImages()
    }

    private fun getFavouriteItems() = viewModelScope.launch {
        favouriteMenuList().collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _getFavouriteMenuItems.update { it.copy(list = response.data) }
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

    fun checkImageExit(url: String) = viewModelScope.launch {
        _imagesUrl.value.list.onEach { value ->
            if (value.URL == url) {
                _isImageExist.update { it.copy(isSuccess = true) }
                _urlId.update { it.copy(id = value.id) }
                return@launch
            }
        }
    }

    fun getNativeAd() = viewModelScope.launch {
        nativeAd.value = googleManager.createNativeAd().apply {
        } ?: run {
            delay(3000)
            googleManager.createNativeAd()
        }
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


    fun removeImage(id: Int) = viewModelScope.launch {
        deleteImage(id).collect { response ->
            when (response) {
                is Either.Right -> {
                    _isLikeSuccess.update { it.copy(isSuccess = true) }
                    fetchAllImages()
                }

                is Either.Left -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.value.message ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }


    private fun fetchAllImages() = viewModelScope.launch {
        favouriteImages().collect { response ->
            when (response) {
                is Either.Right -> {
                    _imagesUrl.update {
                        it.copy(list = response.value)
                    }
                }

                is Either.Left -> {
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.value.message ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

    fun addImage(url: String) = viewModelScope.launch {
        if (imagesUrl.value.list.any { it.URL == url }) {
            _isQueryError.update { it.copy(isError = true, errorMessage = "Image already saved") }
            return@launch
        }

        insertImage(url).collect { response ->
            when (response) {
                is Either.Right -> {
                    _isLikeSuccess.update { it.copy(isSuccess = true) }
                    fetchAllImages()
                    delay(2000L)
                    checkImageExit(url)
                }

                is Either.Left -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.value.message ?: "An error occurred"
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