package com.thezayin.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.ads.GoogleManager
import com.thezayin.domain.usecase.FavouriteImages
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetLoadingState
import com.thezayin.entities.LikeImageModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val googleManager: GoogleManager,
    private val favouriteImages: FavouriteImages,
) : ViewModel() {
    private val _imagesUrl = MutableStateFlow(ImagesUrl())
    val imagesUrl = _imagesUrl.asStateFlow()


    private val _isQueryError = MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    var nativeAd = mutableStateOf<NativeAd?>(null)
        private set

    init {
        fetchAllImages()
    }

    fun getNativeAd() = viewModelScope.launch {
        nativeAd.value = googleManager.createNativeAd().apply {
        } ?: run {
            delay(3000)
            googleManager.createNativeAd()
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


    data class ImagesUrl(
        val list: List<LikeImageModel> = emptyList()
    )
}