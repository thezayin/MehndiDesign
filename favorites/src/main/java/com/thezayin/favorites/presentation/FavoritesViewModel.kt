package com.thezayin.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.favorites.domain.usecase.GetFavoritesUseCase
import com.thezayin.favorites.domain.usecase.RemoveFavoriteUseCase
import com.thezayin.favorites.presentation.event.FavoritesEvent
import com.thezayin.favorites.presentation.state.FavoritesState
import com.thezayin.framework.admob.domain.repository.RewardedAdManager
import com.thezayin.framework.analytics.analytics.Analytics
import com.thezayin.framework.config.RemoteConfig
import com.thezayin.framework.session.image.ImageSession
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the favorites screen.
 *
 * This ViewModel is responsible for fetching the list of favorite images, removing images from favorites,
 * and interacting with various services such as ads and analytics. It holds the UI state and responds to events
 * from the view, performing necessary actions (such as loading favorites, removing favorites, etc.).
 */
class FavoritesViewModel(
    private val getFavorites: GetFavoritesUseCase, // Use case for fetching the favorites
    private val removeFavorite: RemoveFavoriteUseCase, // Use case for removing a favorite image
    private val session: ImageSession, // Session manager for selected images
    val adManager: RewardedAdManager, // Manager for showing rewarded ads
    val remoteConfig: RemoteConfig, // Remote configuration for ads and other settings
    val analytics: Analytics, // Analytics service for tracking events
) : ViewModel() {

    // Mutable state for managing UI state in the favorites screen
    private val _state = MutableStateFlow(FavoritesState())
    val state: StateFlow<FavoritesState> = _state.asStateFlow() // Publicly exposed state

    init {
        // Load the favorites as soon as the ViewModel is initialized
        onEvent(FavoritesEvent.Load)
    }

    /**
     * Handles events triggered from the UI.
     *
     * This method updates the state of the ViewModel based on the event received. It performs actions
     * like loading the favorites, removing an image, or showing/hiding ads.
     */
    fun onEvent(event: FavoritesEvent) {
        when (event) {
            FavoritesEvent.Load -> handleLoadFavorites()
            is FavoritesEvent.Remove -> handleRemoveFavorite(event)
            is FavoritesEvent.Click -> handleClick(event)
            is FavoritesEvent.ShowBannerAd -> handleShowBannerAd()
            is FavoritesEvent.ShowImageSelectionAd -> handleShowImageSelectionAd()
        }
    }

    private fun handleLoadFavorites() {
        loadFavorites()
    }

    private fun handleRemoveFavorite(event: FavoritesEvent.Remove) {
        remove(event.image)
    }

    private fun handleClick(event: FavoritesEvent.Click) {
        session.select(event.image)
    }

    private fun handleShowBannerAd() {
        _state.update { it.copy(showBannerAd = remoteConfig.adConfigs.favoritesBannerAd) }
    }

    private fun handleShowImageSelectionAd() {
        _state.update { it.copy(showImageSelectionAd = remoteConfig.adConfigs.favoritesImageSelectionAd) }
    }

    /**
     * Loads the list of favorite images.
     *
     * This method fetches the list of favorite images using the [GetFavoritesUseCase] and updates the UI state
     * based on whether the operation is loading, successful, or has resulted in an error.
     */
    private fun loadFavorites() = viewModelScope.launch {
        getFavorites().collect { res ->
            when (res) {
                // Show loading state while fetching the data
                is Response.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }

                // On successful data fetching, update the state with the list of favorites
                is Response.Success -> {
                    _state.update { it.copy(favorites = res.data) }
                    _state.update { it.copy(isLoading = false, error = null) }
                }

                // On error, update the state with the error message
                is Response.Error -> {
                    _state.update { it.copy(isLoading = false, error = res.e) }
                }
            }
        }
    }

    /**
     * Removes an image from the list of favorites.
     *
     * This method calls the [RemoveFavoriteUseCase] to remove the image and updates the state accordingly.
     * If the removal is successful, the list of favorites is reloaded; otherwise, an error message is shown.
     */
    private fun remove(image: com.thezayin.framework.model.Image) {
        viewModelScope.launch {
            val ok = removeFavorite(image)
            if (ok) {
                // Reload favorites if the image was successfully removed
                loadFavorites()
            } else {
                // Show error message if the image could not be removed
                _state.update { it.copy(error = "Failed to remove") }
            }
        }
    }
}
