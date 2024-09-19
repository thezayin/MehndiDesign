package com.thezayin.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.ads.GoogleManager
import com.thezayin.databases.models.LikeImageModel
import com.thezayin.domain.model.PreviewMenu
import com.thezayin.domain.usecase.DeleteImage
import com.thezayin.domain.usecase.FavouriteImages
import com.thezayin.domain.usecase.GetFavouriteMenuList
import com.thezayin.domain.usecase.InsertImage
import com.thezayin.domain.usecase.PreviewItems
import com.thezayin.domain.usecase.SaveImage
import com.thezayin.framework.config.RemoteConfig
import com.thezayin.framework.utils.Response
import com.thezayin.presentation.event.PreviewEvents
import com.thezayin.presentation.state.PreviewUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for handling preview UI logic.
 * Manages the state and interactions related to images, menu items, favorites, and Google Ads.
 */
@Suppress("SameParameterValue")
class PreviewViewModel(
    val googleManager: GoogleManager,
    val remoteConfig: RemoteConfig,
    private val saveImage: SaveImage,
    private val menuItemsUseCase: PreviewItems,
    private val deleteImageUseCase: DeleteImage,
    private val insertImageUseCase: InsertImage,
    private val favouriteImagesUseCase: FavouriteImages,
    private val getFavouriteMenuListUseCase: GetFavouriteMenuList
) : ViewModel() {

    // State management for various UI elements
    private val _previewUIState = MutableStateFlow(PreviewUIState())
    val previewUIState = _previewUIState.asStateFlow()

    // Ad management for Native Ads
    var nativeAd = mutableStateOf<NativeAd?>(null)
        private set

    init {
        fetchAllFavouriteImages()
    }

    /**
     * Handles the various UI events from the preview screen.
     */
    private fun handlePreviewUiEvents(event: PreviewEvents) {
        when (event) {
            is PreviewEvents.DisplayErrorMessage -> {
                _previewUIState.update { it.copy(errorMessage = event.errorMessage) }
            }

            is PreviewEvents.RetrieveBottomMenuItems -> {
                _previewUIState.update { it.copy(menuItems = event.menuItems) }
            }

            is PreviewEvents.RetrieveFavoriteImages -> {
                _previewUIState.update { it.copy(favoriteImagesList = event.favoriteImages) }
            }

            is PreviewEvents.ImageExistsInFavorites -> {
                _previewUIState.update { it.copy(isImageInFavorites = event.isSuccess) }
            }

            PreviewEvents.HideErrorDialog -> {
                _previewUIState.update { it.copy(showErrorDialog = false) }
            }

            PreviewEvents.HideLoading -> {
                _previewUIState.update { it.copy(isLoading = false) }
            }

            PreviewEvents.ShowErrorDialog -> {
                _previewUIState.update { it.copy(showErrorDialog = true) }
            }

            PreviewEvents.ShowLoading -> {
                _previewUIState.update { it.copy(isLoading = true) }
            }

            is PreviewEvents.LikeActionSuccess -> {
                _previewUIState.update { it.copy(isLikeActionSuccessful = event.isSuccess) }
            }

            is PreviewEvents.RetrieveImageId -> {
                _previewUIState.update { it.copy(imageId = event.imageId) }
            }

            PreviewEvents.SaveImageStart -> {
                _previewUIState.update {
                    it.copy(
                        isSavingImage = true,
                        saveImageSuccess = false,
                        saveImageProgressMessage = "Saving..."
                    )
                }
            }

            is PreviewEvents.SaveImageComplete -> {
                _previewUIState.update {
                    it.copy(
                        isSavingImage = false,
                        saveImageSuccess = true,
                        saveImageProgressMessage = event.message
                    )
                }
            }

            is PreviewEvents.SaveSuccess -> {
                _previewUIState.update { it.copy(saveImageSuccess = event.isSuccess) }
            }
        }
    }

    /**
     * Initiates the process to save an image from the provided URL.
     */
    fun saveImageFromUrl(url: String) = viewModelScope.launch {
        saveImage(url).collect { response ->
            when (response) {
                is Response.Loading -> {}

                is Response.Success -> {
                    saveImageMessage("Image saved successfully!")
                }

                is Response.Error -> {
                    saveImageMessage("Failed to save image: ${response.e}")
                }
            }
        }
    }

    /**
     * Checks if the image exists in the list of favourite images.
     */
    fun checkIfImageExists(url: String) = viewModelScope.launch {
        _previewUIState.update { it.copy(isLoading = true) }
        val imageExists = previewUIState.value.favoriteImagesList.any { it.URL == url }
        if (imageExists) {
            setImageExistState(true)
            fetchFavouriteItems()
            previewUIState.value.favoriteImagesList.first { it.URL == url }.id?.let { setImageId(it) }
        } else {
            fetchMenuItems()
            setImageExistState(false)
        }
        hideLoading()
    }

    /**
     * Fetches and sets a Native Ad through GoogleManager.
     */
    fun loadNativeAd() = viewModelScope.launch {
        nativeAd.value = googleManager.createNativeAd().apply {
        } ?: run {
            delay(3000)
            googleManager.createNativeAd()
        }
    }

    /**
     * Fetches favourite menu items and updates the state accordingly.
     */
    private fun fetchFavouriteItems() = viewModelScope.launch {
        getFavouriteMenuListUseCase().collect { response ->
            when (response) {
                is Response.Success -> {
                    hideLoading()
                    updateBottomMenu(response.data)
                }

                is Response.Error -> {
                    showErrorDialog()
                    showErrorMessage(response.e)
                }

                is Response.Loading -> {
                    showLoading()
                    hideErrorDialog()
                }
            }
        }
    }

    /**
     * Fetches all menu items and updates the state accordingly.
     */
    private fun fetchMenuItems() = viewModelScope.launch {
        menuItemsUseCase().collect { response ->
            when (response) {
                is Response.Success -> {
                    hideLoading()
                    updateBottomMenu(response.data)
                }

                is Response.Error -> {
                    showErrorDialog()
                    showErrorMessage(response.e)
                }

                is Response.Loading -> {
                    showLoading()
                    hideErrorDialog()
                }
            }
        }
    }

    /**
     * Removes an image from the favourite list.
     */
    fun removeFavouriteImage(id: Int) = viewModelScope.launch {
        deleteImageUseCase(id).collect { response ->
            when (response) {
                is Either.Right -> {
                    hideLoading()
                    setImageExistState(true)
                    fetchAllFavouriteImages()
                }

                is Either.Left -> {
                    hideLoading()
                    showErrorDialog()
                    showErrorMessage(response.value.message ?: "An error occurred")
                }
            }
        }
    }

    /**
     * Fetches all images marked as favourite.
     */
    private fun fetchAllFavouriteImages() = viewModelScope.launch {
        favouriteImagesUseCase().collect { response ->
            when (response) {
                is Either.Right -> {
                    hideLoading()
                    updateFavouriteImages(response.value)
                }

                is Either.Left -> {
                    hideLoading()
                    showErrorDialog()
                    showErrorMessage(response.value.message ?: "An error occurred")
                }
            }
        }
    }

    /**
     * Adds an image to the favourite list.
     */
    fun addFavouriteImage(url: String) = viewModelScope.launch {
        if (previewUIState.value.favoriteImagesList.any { it.URL == url }) {
            hideLoading()
            showErrorDialog()
            showErrorMessage("Image already exists in favourite list")
            return@launch
        }

        insertImageUseCase(url).collect { response ->
            when (response) {
                is Either.Right -> {
                    setLikeSuccessState(true)
                    fetchAllFavouriteImages()
                    delay(2000L)
                    checkIfImageExists(url)
                }

                is Either.Left -> {
                    hideLoading()
                    showErrorDialog()
                    showErrorMessage(response.value.message ?: "An error occurred")
                }
            }
        }
    }

    // UI Helper Functions

    private fun showErrorMessage(error: String) {
        handlePreviewUiEvents(PreviewEvents.DisplayErrorMessage(error))
    }

    private fun updateBottomMenu(menu: List<PreviewMenu>) {
        handlePreviewUiEvents(PreviewEvents.RetrieveBottomMenuItems(menu))
    }

    private fun updateFavouriteImages(list: List<LikeImageModel>) {
        handlePreviewUiEvents(PreviewEvents.RetrieveFavoriteImages(list))
    }

    private fun setImageExistState(isSuccess: Boolean) {
        handlePreviewUiEvents(PreviewEvents.ImageExistsInFavorites(isSuccess))
    }

    private fun showErrorDialog() {
        handlePreviewUiEvents(PreviewEvents.ShowErrorDialog)
    }

    private fun hideErrorDialog() {
        handlePreviewUiEvents(PreviewEvents.HideErrorDialog)
    }

    private fun hideLoading() {
        handlePreviewUiEvents(PreviewEvents.HideLoading)
    }

    private fun showLoading() {
        handlePreviewUiEvents(PreviewEvents.ShowLoading)
    }

    private fun setLikeSuccessState(isSuccess: Boolean) {
        handlePreviewUiEvents(PreviewEvents.LikeActionSuccess(isSuccess))
    }

    private fun setImageId(imageId: Int) {
        handlePreviewUiEvents(PreviewEvents.RetrieveImageId(imageId))
    }

    /**
     * Resets the save image message to null after displaying.
     */
    fun resetSaveImageMessage() {
        handlePreviewUiEvents(PreviewEvents.SaveImageComplete(""))
    }

    private fun saveImageMessage(message: String) {
        handlePreviewUiEvents(PreviewEvents.SaveImageComplete(message))
    }

    fun startSaveImage() {
        handlePreviewUiEvents(PreviewEvents.SaveImageStart)
    }
}
