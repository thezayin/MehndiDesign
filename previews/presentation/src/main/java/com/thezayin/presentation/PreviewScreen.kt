package com.thezayin.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
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
    imageUrl: String,
    onBackClick: () -> Unit
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

    // Call the composable that handles the screen layout and behavior
    PreviewScreenContent(
        activity = activity,
        imageId = uiState.imageId,
        imageUrl = imageUrl,
        menuItems = uiState.menuItems,
        showLoading = uiState.isLoading,
        currentNativeAd = nativeAdState.value,
        imageExistsInFavorites = uiState.isImageInFavorites,
        imageSavedSuccess = uiState.isLikeActionSuccessful,
        coroutineScope = coroutineScope,
        onBackButtonClick = onBackClick,
        fetchNativeAd = { viewModel.loadNativeAd() },
        removeImageFromFavorites = { viewModel.removeFavouriteImage(it) },
        addImageToFavorites = { viewModel.addFavouriteImage(it) },
        verifyImageExistenceInFavorites = { viewModel.checkIfImageExists(imageUrl) }
    )
}
