// presentation/PreviewViewModel.kt
package com.thezayin.preview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.framework.admob.domain.repository.InterstitialAdManager
import com.thezayin.framework.admob.domain.repository.RewardedAdManager
import com.thezayin.framework.analytics.analytics.Analytics
import com.thezayin.framework.config.RemoteConfig
import com.thezayin.preview.domain.usecase.DownloadImageUseCase
import com.thezayin.preview.domain.usecase.GetSelectedImageUseCase
import com.thezayin.preview.domain.usecase.IsImageLikedUseCase
import com.thezayin.preview.domain.usecase.ShareImageUseCase
import com.thezayin.preview.domain.usecase.ToggleLikeUseCase
import com.thezayin.preview.presentation.event.PreviewEvent
import com.thezayin.preview.presentation.state.PreviewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the state and actions related to the image preview screen.
 *
 * This class handles various operations such as:
 * - Loading the selected image
 * - Handling like/unlike actions
 * - Downloading images
 * - Sharing images via different platforms
 * - Handling ad events (Interstitial and Rewarded ads)
 * - Managing UI state like displaying success dialogs, errors, and ads
 */
class PreviewViewModel(
    private val getImage: GetSelectedImageUseCase,
    private val isImageLiked: IsImageLikedUseCase,
    private val toggleLike: ToggleLikeUseCase,
    private val downloadImage: DownloadImageUseCase,
    private val shareImage: ShareImageUseCase,
    val interstitialAdManager: InterstitialAdManager,
    val rewardedAdManager: RewardedAdManager,
    val remoteConfig: RemoteConfig,
    val analytics: Analytics,
) : ViewModel() {

    private val _state = MutableStateFlow(PreviewState())
    val state: StateFlow<PreviewState> = _state.asStateFlow()

    init {
        onEvent(PreviewEvent.Load)
    }

    /**
     * Handles the UI events by dispatching them to appropriate functions
     */
    fun onEvent(event: PreviewEvent) {
        when (event) {
            PreviewEvent.Load -> loadImage()  // Load the selected image
            PreviewEvent.Like -> toggleLiked()  // Toggle like/unlike status of the image
            PreviewEvent.Share -> share()  // Share the image via a platform
            PreviewEvent.Download -> download()  // Download the image
            PreviewEvent.ResetShareFlag -> resetShareFlag() // Reset share flag
            PreviewEvent.ResetDownloadFlag -> resetDownloadFlag() // Reset download flag
            PreviewEvent.Back -> handleBackNavigation()  // Handle back navigation
            is PreviewEvent.ShowBannerAd -> updateAdState() // Update ad state for banner
            is PreviewEvent.ShowDownloadAd -> updateDownloadAdState() // Update ad state for download
            is PreviewEvent.ShowShareAd -> updateShareAdState() // Update ad state for share
            is PreviewEvent.ShowSaveSuccessDialog -> updateSaveSuccessDialogState(event) // Show/hide save success dialog
        }
    }

    private fun loadImage() = viewModelScope.launch {
        val img = getImage()
        if (img != null) {
            val liked = isImageLiked(img)
            _state.value = PreviewState(
                image = img,
                imageUrl = img.image_url,
                isLiked = liked
            )
        } else {
            _state.value =
                PreviewState(error = "No image selected.")  // If no image is selected, update error state
        }
    }

    private fun toggleLiked() = viewModelScope.launch {
        val currentImage = _state.value.image ?: return@launch
        val newLikedStatus = toggleLike(currentImage)
        _state.value = _state.value.copy(isLiked = newLikedStatus)
    }

    private fun download() = viewModelScope.launch {
        val img = _state.value.image ?: return@launch
        try {
            downloadImage(img)
            _state.update { it.copy(downloadSuccess = true) }
            onEvent(PreviewEvent.ShowSaveSuccessDialog(true))
        } catch (t: Throwable) {
            _state.update { it.copy(error = t.message) }
        }
    }

    private fun share() = viewModelScope.launch {
        _state.value.image?.let { img ->
            runCatching { shareImage(img) }
                .onSuccess { uri ->
                    _state.update { it.copy(shareUri = uri) }
                }
                .onFailure { t ->
                    _state.update { it.copy(error = t.message) }
                }
        }
    }

    private fun resetShareFlag() {
        _state.update { it.copy(shareUri = null) }
    }

    private fun resetDownloadFlag() {
        _state.update { it.copy(downloadSuccess = false) }
    }

    private fun handleBackNavigation() {
        // Handle the back navigation, e.g., pop the backstack or navigate to a different screen
    }

    private fun updateAdState() {
        _state.update { it.copy(showBannerAd = remoteConfig.adConfigs.previewBannerAd) }
    }

    private fun updateDownloadAdState() {
        _state.update { it.copy(showDownloadAd = remoteConfig.adConfigs.imageDownloadAd) }
    }

    private fun updateShareAdState() {
        _state.update { it.copy(showShareAd = remoteConfig.adConfigs.imageShareAd) }
    }

    private fun updateSaveSuccessDialogState(event: PreviewEvent.ShowSaveSuccessDialog) {
        _state.update { it.copy(isSaveSuccessDialogVisible = event.show) }
    }
}
