package com.thezayin.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.ads.GoogleManager
import com.thezayin.databases.models.LikeImageModel
import com.thezayin.domain.usecase.FavouriteImages
import com.thezayin.presentation.events.FavouriteEvents
import com.thezayin.presentation.state.FavouriteState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the state and events of the Favourite screen.
 *
 * @property googleManager Manages Google ads.
 * @property FavouriteImages Use case for fetching favourite images.
 */
class FavouriteViewModel(
    private val googleManager: GoogleManager,
    private val favouriteImagesUseCase: FavouriteImages,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavouriteState())
    val uiState = _uiState.asStateFlow()

     var currentNativeAd = mutableStateOf<NativeAd?>(null)

    init {
        loadFavouriteImages()
    }

    /**
     * Handles various events related to the Favourite screen and updates the state.
     *
     * @param event The event to handle.
     */
    private fun handleEvent(event: FavouriteEvents) {
        when (event) {
            is FavouriteEvents.ErrorMessage -> {
                _uiState.update { it.copy(errorMessage = event.errorMessage) }
            }

            is FavouriteEvents.GetImages -> {
                _uiState.update { it.copy(list = event.list) }
            }

            FavouriteEvents.HideLoading -> {
                _uiState.update { it.copy(isLoading = false) }
            }

            FavouriteEvents.ShowErrorDialog -> {
                _uiState.update { it.copy(errorDialog = true) }
            }
        }
    }

    /**
     * Fetches a native ad and updates the current ad state.
     */
    fun loadNativeAd() = viewModelScope.launch {
        currentNativeAd.value = googleManager.createNativeAd().apply {
            if (this == null) {
                delay(3000)
                currentNativeAd.value = googleManager.createNativeAd()
            }
        }
    }

    /**
     * Fetches all favourite images and handles the result.
     */
    private fun loadFavouriteImages() = viewModelScope.launch {
        favouriteImagesUseCase()
            .catch { exception ->
                // Handle the exception by showing an error message and dialog
                showError(exception.localizedMessage ?: "An unknown error occurred")
                showErrorDialog()
                hideLoading()
            }
            .collect { result ->
                when (result) {
                    is Either.Right -> {
                        updateImages(result.value)
                        delay(3000) // Optional delay to simulate loading
                        hideLoading()
                    }

                    is Either.Left -> {
                        showErrorDialog()
                        hideLoading()
                    }
                }
            }
    }

    /**
     * Updates the state with a new list of images.
     *
     * @param images The list of favourite images.
     */
    private fun updateImages(images: List<LikeImageModel>) {
        handleEvent(FavouriteEvents.GetImages(images))
    }

    /**
     * Updates the state to hide the loading indicator.
     */
    private fun hideLoading() {
        handleEvent(FavouriteEvents.HideLoading)
    }

    /**
     * Updates the state to show an error dialog.
     */
    private fun showErrorDialog() {
        handleEvent(FavouriteEvents.ShowErrorDialog)
    }

    /**
     * Updates the state with an error message.
     *
     * @param message The error message to display.
     */
    private fun showError(message: String) {
        handleEvent(FavouriteEvents.ErrorMessage(message))
    }
}
