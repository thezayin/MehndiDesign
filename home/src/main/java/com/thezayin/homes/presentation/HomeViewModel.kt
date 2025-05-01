package com.thezayin.homes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.thezayin.framework.admob.domain.repository.InterstitialAdManager
import com.thezayin.framework.admob.domain.repository.RewardedAdManager
import com.thezayin.framework.analytics.analytics.Analytics
import com.thezayin.framework.config.RemoteConfig
import com.thezayin.framework.model.Category
import com.thezayin.framework.model.Image
import com.thezayin.framework.session.category.CategorySession
import com.thezayin.framework.session.image.ImageSession
import com.thezayin.framework.utils.Response
import com.thezayin.homes.domain.usecase.GetCategoriesUseCase
import com.thezayin.homes.domain.usecase.GetImagesUseCase
import com.thezayin.homes.presentation.event.HomeEvent
import com.thezayin.homes.presentation.state.HomeState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for managing home screen state.
 * It handles loading categories, images, and ads, while managing the UI state and responding to events.
 */
class HomeViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getImagesUseCase: GetImagesUseCase,
    private val imageSession: ImageSession,
    private val categorySession: CategorySession,
    private val remoteConfig: RemoteConfig,
     val interstitialAdManager: InterstitialAdManager,
     val rewardedAdManager: RewardedAdManager,
     val analytics: Analytics
) : ViewModel() {

    // State Flow for UI updates
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        // Load categories and images on initialization
        loadCategories()
        loadImages()
    }

    /**
     * Handles events triggered by the UI, updating the state accordingly.
     * This centralizes state changes based on the event type.
     */
    fun handleEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ShowLoading -> updateLoadingState(true)
            is HomeEvent.HideLoading -> updateLoadingState(false)
            is HomeEvent.ShowErrorDialog -> updateErrorDialogState(true)
            is HomeEvent.HideErrorDialog -> updateErrorDialogState(false)
            is HomeEvent.ErrorMessage -> updateErrorMessage(event.errorMessage)
            is HomeEvent.HomeImages -> updateHomeImages(event.homeImages)
            is HomeEvent.HomeCategories -> updateHomeCategories(event.categories)
            is HomeEvent.SelectImage -> imageSession.select(event.image)
            is HomeEvent.SelectCategory -> categorySession.select(event.category)
            is HomeEvent.ShowBannerAd -> updateAdState(remoteConfig.adConfigs.homeBannerAd)
            is HomeEvent.ShowCategorySelectionAd -> updateAdState(remoteConfig.adConfigs.categorySelectionAd)
            is HomeEvent.ShowImageSelectionAd -> updateAdState(remoteConfig.adConfigs.homeImageSelectionAd)
            is HomeEvent.ShowMyLikeClickAd -> updateAdState(remoteConfig.adConfigs.myLikeSelectionAd)
        }
    }

    /**
     * Loads categories by calling the [GetCategoriesUseCase] and updates the state based on the result.
     */
    private fun loadCategories() = viewModelScope.launch {
        getCategoriesUseCase().collect { response ->
            when (response) {
                is Response.Loading -> handleEvent(HomeEvent.ShowLoading)
                is Response.Success -> handleCategoriesSuccess(response.data)
                is Response.Error -> handleCategoriesError(response.e)
            }
        }
    }

    /**
     * Handles the success response for loading categories.
     * Updates the UI state with the fetched categories.
     */
    private fun handleCategoriesSuccess(categories: List<Category>) {
        handleEvent(HomeEvent.HideLoading)
        handleEvent(HomeEvent.HomeCategories(categories))
    }

    /**
     * Handles the error response for loading categories.
     * Updates the UI state with the error message.
     */
    private fun handleCategoriesError(error: String) {
        handleEvent(HomeEvent.HideLoading)
        handleEvent(HomeEvent.ErrorMessage(error))
    }

    /**
     * Loads images by calling the [GetImagesUseCase] and updates the state based on the result.
     */
    private fun loadImages() = viewModelScope.launch {
        getImagesUseCase().collect { response ->
            when (response) {
                is Response.Loading -> handleEvent(HomeEvent.ShowLoading)
                is Response.Success -> handleImagesSuccess(response.data)
                is Response.Error -> handleImagesError(response.e)
            }
        }
    }

    /**
     * Handles the success response for loading images.
     * Updates the UI state with the fetched images.
     */
    private fun handleImagesSuccess(images: Flow<PagingData<Image>>) {
        handleEvent(HomeEvent.HideLoading)
        handleEvent(HomeEvent.HomeImages(images))
    }

    /**
     * Handles the error response for loading images.
     * Updates the UI state with the error message.
     */
    private fun handleImagesError(error: String) {
        handleEvent(HomeEvent.HideLoading)
        handleEvent(HomeEvent.ErrorMessage(error))
    }

    /**
     * Updates the loading state.
     */
    private fun updateLoadingState(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }

    /**
     * Updates the error dialog visibility.
     */
    private fun updateErrorDialogState(isVisible: Boolean) {
        _state.update { it.copy(errorDialog = isVisible) }
    }

    /**
     * Updates the error message displayed in the UI.
     */
    private fun updateErrorMessage(message: String) {
        _state.update { it.copy(errorMessage = message) }
    }

    /**
     * Updates the state for images and categories.
     */
    private fun updateHomeImages(images: Flow<PagingData<Image>>) {
        _state.update { it.copy(homeImages = images) }
    }

    /**
     * Updates the state for categories.
     */
    private fun updateHomeCategories(categories: List<Category>) {
        _state.update { it.copy(homeCategories = categories) }
    }

    /**
     * Updates the state for ad visibility.
     */
    private fun updateAdState(adConfig: Boolean) {
        _state.update { it.copy(showBannerAd = adConfig) }
    }
}
