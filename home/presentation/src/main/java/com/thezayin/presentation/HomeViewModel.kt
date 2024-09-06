package com.thezayin.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.ads.GoogleManager
import com.thezayin.domain.usecase.FetchRemote
import com.thezayin.domain.usecase.GetHomeImages
import com.thezayin.domain.usecase.HomeCategories
import com.thezayin.entities.GetCategoryState
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetImageState
import com.thezayin.entities.GetLoadingState
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val googleManager: GoogleManager,
    private val getHomeCategories: HomeCategories,
    private val fetchHome: GetHomeImages,
    private val fetchRemote: FetchRemote,
) : ViewModel() {

    private val _homeImages = MutableStateFlow(GetImageState())
    val homeImages = _homeImages.asStateFlow()

    private val _homeCategories = MutableStateFlow(GetCategoryState())
    val homeCategories = _homeCategories.asStateFlow()

    private val _isQueryError = MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    var nativeAd = mutableStateOf<NativeAd?>(null)
        private set

    init {
        getImages()
        fetchHomeCategories()
    }


    fun getNativeAd() = viewModelScope.launch {
        nativeAd.value = googleManager.createNativeAd().apply {
        } ?: run {
            delay(3000)
            googleManager.createNativeAd()
        }
    }

    private fun fetchHomeCategories() = viewModelScope.launch {
        getHomeCategories().collect { response ->
            when (response) {
                is Response.Success -> {
                    _homeCategories.update { it.copy(list = response.data) }
                }

                else -> {
                    Log.d("CategoriesViewModel", "fetchHomeCategories: $response")
                }
            }
        }
    }

    private fun getImages() = viewModelScope.launch {
        fetchRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    fetchHomeImages()
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

    private fun fetchHomeImages() = viewModelScope.launch {
        fetchHome().collect { response ->
            when (response) {
                is Response.Success -> {
                    _homeImages.update { it.copy(list = response.data) }
                    delay(2000L)
                    _isLoading.update { it.copy(isLoading = false) }
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