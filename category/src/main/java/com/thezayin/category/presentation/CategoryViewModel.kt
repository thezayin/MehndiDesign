package com.thezayin.category.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.thezayin.category.domain.usecase.GetImagesForCategoryUseCase
import com.thezayin.category.presentation.event.CategoryEvent
import com.thezayin.category.presentation.state.CategoryState
import com.thezayin.framework.admob.domain.repository.RewardedAdManager
import com.thezayin.framework.analytics.analytics.Analytics
import com.thezayin.framework.config.RemoteConfig
import com.thezayin.framework.model.Image
import com.thezayin.framework.session.category.CategorySession
import com.thezayin.framework.session.image.ImageSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * ViewModel for managing the Category screen state.
 * This ViewModel is responsible for loading images for a selected category, managing ad visibility,
 * and interacting with session managers for category and image data.
 */
class CategoryViewModel(
    private val getImagesForCategory: GetImagesForCategoryUseCase,
    private val categorySession: CategorySession,
    private val imageSession: ImageSession,
    val rewardedAdManager: RewardedAdManager,
    val remoteConfig: RemoteConfig,
    val analytics: Analytics,
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryState())
    val state: StateFlow<CategoryState> = _state.asStateFlow()

    init {
        // Load images when ViewModel is initialized
        onEvent(CategoryEvent.Load)
    }

    /**
     * Handles incoming events related to the Category screen.
     * This includes loading images, selecting images, and showing ads.
     */
    fun onEvent(event: CategoryEvent) {
        when (event) {
            CategoryEvent.Load -> loadImages() // Load images for the selected category
            is CategoryEvent.ClickImage -> selectImage(event.image) // Select an image
            is CategoryEvent.ShowBannerAd -> updateBannerAdVisibility() // Show/hide banner ad
            is CategoryEvent.ShowImageSelectionAd -> updateImageSelectionAdVisibility() // Show/hide image selection ad
        }
    }

    /**
     * Loads images for the currently selected category and updates the UI state accordingly.
     * If no category is selected, an error message is displayed.
     */
    private fun loadImages() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true, error = null) }

        // Get selected category from session
        val selectedCat = categorySession.getSelected()
        if (selectedCat == null) {
            handleError("No category selected.") // Handle error if no category is selected
            return@launch
        }

        try {
            // Fetch images for the selected category
            val imgs = getImagesForCategory(selectedCat.id)
            _state.update { it.copy(isLoading = false, images = imgs) }
            Timber.d("Images for category ${selectedCat.id} loaded successfully.")
        } catch (t: Throwable) {
            handleError("Failed to load images: ${t.message}") // Handle any errors during the image loading process
        }
    }

    /**
     * Selects the clicked image and updates the image session.
     *
     * @param image The image that was clicked.
     */
    private fun selectImage(image: Image) {
        Timber.d("Image clicked: ${image.id}")
        imageSession.select(image)
    }

    private fun updateBannerAdVisibility() {
        _state.update { it.copy(showBannerAd = remoteConfig.adConfigs.categoryBannerAd) }
        Timber.d("Banner Ad visibility updated: ${remoteConfig.adConfigs.categoryBannerAd}")
    }

    private fun updateImageSelectionAdVisibility() {
        _state.update { it.copy(showImageSelectionAd = remoteConfig.adConfigs.categoryImageSelectionAd) }
        Timber.d("Image selection ad visibility updated: ${remoteConfig.adConfigs.categoryImageSelectionAd}")
    }

    /**
     * Handles errors during image loading or any other failure.
     * Logs the error and updates the UI state to show the error message.
     *
     * @param errorMessage The error message to be displayed.
     */
    private fun handleError(errorMessage: String) {
        _state.update { it.copy(isLoading = false, error = errorMessage) }
        Timber.e("Error occurred: $errorMessage")
        FirebaseCrashlytics.getInstance().log("CategoryViewModel error: $errorMessage")
    }
}
