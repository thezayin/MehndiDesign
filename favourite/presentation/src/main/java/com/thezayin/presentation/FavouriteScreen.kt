package com.thezayin.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.thezayin.presentation.component.FavouriteScreenContent
import org.koin.compose.koinInject

/**
 * Composable function that displays the Favourite Screen.
 * It uses the FavouriteViewModel to manage the UI state and interactions.
 *
 * @param onBackClick Callback for handling the back button action.
 * @param onImageClick Callback for handling image click actions.
 */
@Composable
fun FavouriteScreen(
    onBackClick: () -> Unit,
    onImageClick: (String) -> Unit
) {
    // Inject the ViewModel using Koin
    val viewModel: FavouriteViewModel = koinInject()

    // Remember the coroutine scope for launching coroutines
    val coroutineScope = rememberCoroutineScope()

    // Get the current native ad from the ViewModel
    val nativeAdState = remember { viewModel.currentNativeAd }

    // State to manage the visibility of the "No items present" view
    val isNoItemVisible = remember { mutableStateOf(false) }

    // Collect the current UI state from the ViewModel
    val uiState = viewModel.uiState.collectAsState().value

    // Render the FavouriteScreenContent with the current state and actions
    FavouriteScreenContent(
        scope = coroutineScope,
        nativeAd = nativeAdState.value,
        isLoading = uiState.isLoading,
        list = uiState.list,
        noItemVisible = isNoItemVisible,
        getNativeAd = { viewModel.loadNativeAd() },
        onBackClick = onBackClick,
        onImageClick = onImageClick
    )
}
