package com.thezayin.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.ads.GoogleManager
import com.thezayin.databases.models.ImageModel
import com.thezayin.domain.model.CategoriesModel
import com.thezayin.domain.usecase.FetchRemote
import com.thezayin.domain.usecase.GetHomeImages
import com.thezayin.domain.usecase.HomeCategories
import com.thezayin.framework.utils.Response
import com.thezayin.presentation.event.HomeEvents
import com.thezayin.presentation.state.HomeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for managing and processing data for the Home screen.
 */
class HomeViewModel(
    private val googleManager: GoogleManager,
    private val getHomeCategoriesUseCase: HomeCategories,
    private val getHomeImagesUseCase: GetHomeImages,
    private val fetchRemoteUseCase: FetchRemote
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    // State for managing the current NativeAd
    var currentNativeAd = mutableStateOf<NativeAd?>(null)
        private set

    init {
        loadImages()
        loadCategories()
    }

    /**
     * Handles various home-related events and updates the state accordingly.
     */
    private fun handleHomeEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.HomeCategories -> _homeState.update { it.copy(homeCategories = event.categories) }
            is HomeEvents.ErrorMessage -> _homeState.update { it.copy(errorMessage = event.errorMessage) }
            is HomeEvents.HomeImages -> _homeState.update { it.copy(homeImages = event.homeImages) }
            HomeEvents.HideErrorDialog -> _homeState.update { it.copy(errorDialog = false) }
            HomeEvents.HideLoading -> _homeState.update { it.copy(isLoading = false) }
            HomeEvents.ShowErrorDialog -> _homeState.update { it.copy(errorDialog = true) }
            HomeEvents.ShowLoading -> _homeState.update { it.copy(isLoading = true) }
        }
    }

    /**
     * Retrieves a native ad and updates the state. Retries after a delay if the ad is not available.
     */
    fun loadNativeAd() = viewModelScope.launch {
        currentNativeAd.value = googleManager.createNativeAd() ?: run {
            delay(3000)
            googleManager.createNativeAd()
        }
    }

    /**
     * Loads home categories and updates the state.
     */
    private fun loadCategories() = viewModelScope.launch {
        getHomeCategoriesUseCase()
            .catch { e ->
                handleError(e.localizedMessage ?: "An error occurred")
                showErrorDialog()
                hideLoading()
            }
            .collect { response ->
                when (response) {
                    is Response.Success -> updateCategories(response.data)
                    else -> Log.d("HomeViewModel", "loadCategories: $response")
                }
            }
    }

    /**
     * Loads images and triggers fetching home images.
     */
    private fun loadImages() = viewModelScope.launch {
        fetchRemoteUseCase().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchHomeImages()
                    hideLoading()
                }
                is Response.Error -> {
                    hideLoading()
                    showErrorDialog()
                    handleError(response.e)
                }
                is Response.Loading -> {
                    showLoading()
                    hideErrorDialog()
                }
            }
        }
    }

    /**
     * Fetches home images and updates the state.
     */
    private fun fetchHomeImages() = viewModelScope.launch {
        getHomeImagesUseCase().collect { response ->
            when (response) {
                is Response.Success -> {
                    updateImages(response.data)
                    delay(2000L)
                    hideLoading()
                }
                is Response.Error -> {
                    hideLoading()
                    showErrorDialog()
                    handleError(response.e)
                }
                is Response.Loading -> {
                    showLoading()
                    hideErrorDialog()
                }
            }
        }
    }

    /**
     * Updates the state with the list of home images.
     */
    private fun updateImages(images: Flow<PagingData<ImageModel>>) {
        handleHomeEvent(HomeEvents.HomeImages(images))
    }

    /**
     * Updates the state with the list of home categories.
     */
    private fun updateCategories(categories: List<CategoriesModel>) {
        handleHomeEvent(HomeEvents.HomeCategories(categories))
    }

    /**
     * Handles error messages by updating the state.
     */
    private fun handleError(error: String) {
        handleHomeEvent(HomeEvents.ErrorMessage(error))
    }

    /**
     * Shows an error dialog by updating the state.
     */
    private fun showErrorDialog() {
        handleHomeEvent(HomeEvents.ShowErrorDialog)
    }

    /**
     * Hides the error dialog by updating the state.
     */
    fun hideErrorDialog() {
        handleHomeEvent(HomeEvents.HideErrorDialog)
    }

    /**
     * Hides the loading indicator by updating the state.
     */
    private fun hideLoading() {
        handleHomeEvent(HomeEvents.HideLoading)
    }

    /**
     * Shows the loading indicator by updating the state.
     */
    private fun showLoading() {
        handleHomeEvent(HomeEvents.ShowLoading)
    }
}