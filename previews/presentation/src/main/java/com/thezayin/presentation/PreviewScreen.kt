package com.thezayin.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.thezayin.framework.extension.ads.showInterstitialAd
import com.thezayin.framework.extension.ads.showRewardedAd
import com.thezayin.presentation.components.PreviewScreenContent
import org.koin.compose.koinInject

/**
 * Composable function that displays the Preview Screen.
 * It uses the PreviewViewModel to manage the UI state and interactions.
 *
 * @param imageUrl URL of the image to be displayed.
 * @param onBackClick Callback for handling the back button action.
 */
@Composable
fun PreviewScreen(
    imageUrl: String, onBackClick: () -> Unit
) {
    // Inject the ViewModel using Koin
    val viewModel: PreviewViewModel = koinInject()

    // Remember the coroutine scope for launching coroutines
    val coroutineScope = rememberCoroutineScope()

    // Get the current activity context
    val activity = LocalContext.current as Activity

    // Remember the native ad state from the ViewModel
    val nativeAdState = remember { viewModel.nativeAd }

    // Collect the current UI state from the ViewModel as a state object
    val uiState = viewModel.previewUIState.collectAsState().value

    val googleManager = viewModel.googleManager
    val remoteConfig = viewModel.remoteConfig.adConfigs
    val showLoadingAd = remoteConfig.nativeAdOnPreviewLoading
    val showBottomAd = remoteConfig.nativeAdOnPreviewScreen
    val showAdOnBackPress = remoteConfig.adOnBackPress
    val adOnImageLike = remoteConfig.adOnImageLike
    val adOnImageRemove = remoteConfig.adOnImageRemove
    val switchAdOnImageRemove = remoteConfig.interstitialToRewardedOnImageRemove
    val switchAdOnImageLike = remoteConfig.interstitialToRewardedOnImageLike
    // Call the composable that handles the screen layout and behavior
    PreviewScreenContent(
        activity = activity,
        imageId = uiState.imageId,
        imageUrl = imageUrl,
        showLoadingAd = showLoadingAd,
        showBottomAd = showBottomAd,
        isSavingImage = uiState.isSavingImage,
        saveImageSuccess = uiState.saveImageSuccess,
        saveImageMessage = uiState.saveImageProgressMessage,
        menuItems = uiState.menuItems,
        showLoading = uiState.isLoading,
        currentNativeAd = nativeAdState.value,
        imageExistsInFavorites = uiState.isImageInFavorites,
        imageSavedSuccess = uiState.isLikeActionSuccessful,
        coroutineScope = coroutineScope,
        onBackButtonClick = {
            showInterstitialAd(
                activity = activity,
                showAd = showAdOnBackPress,
                manager = googleManager,
                callBack = { onBackClick() })
        },
        fetchNativeAd = { viewModel.loadNativeAd() },
        removeImageFromFavorites = { id ->
            if (switchAdOnImageRemove) {
                showRewardedAd(context = activity,
                    showAd = adOnImageRemove,
                    googleManager = googleManager,
                    callback = { viewModel.removeFavouriteImage(id) })
            } else {
                showInterstitialAd(
                    activity = activity,
                    showAd = adOnImageRemove,
                    manager = googleManager,
                    callBack = { viewModel.removeFavouriteImage(id) })
            }
        },
        addImageToFavorites = { image ->
            if (switchAdOnImageLike) {
                showRewardedAd(context = activity,
                    showAd = adOnImageLike,
                    googleManager = googleManager,
                    callback = { viewModel.addFavouriteImage(image) })
            } else {
                showInterstitialAd(activity = activity,
                    showAd = adOnImageLike,
                    manager = googleManager,
                    callBack = { viewModel.addFavouriteImage(image) })
            }
        },
        verifyImageExistenceInFavorites = { viewModel.checkIfImageExists(imageUrl) },
        saveImage = { viewModel.saveImageFromUrl(imageUrl) }, // Pass saveImage lambda
        resetSaveImageMessage = { viewModel.resetSaveImageMessage() } // Pass reset function
    )
}
