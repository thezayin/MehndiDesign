package com.thezayin.presentation

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.paging.compose.collectAsLazyPagingItems
import com.thezayin.presentation.component.HomeContent
import org.koin.compose.koinInject

/**
 * HomeScreen displays the main user interface for the home feature.
 *
 * @param onCategoryClick Callback for when a category is clicked.
 * @param onMoreCategoryClick Callback for when "More" category is clicked.
 * @param onImageClick Callback for when an image is clicked.
 * @param onLikeClick Callback for when a like action is performed.
 * @param onSettingClick Callback for when settings are clicked.
 */
@Composable
fun HomeScreen(
    onCategoryClick: (Int, String?) -> Unit,
    onMoreCategoryClick: () -> Unit,
    onImageClick: (String) -> Unit,
    onLikeClick: () -> Unit,
    onSettingClick: () -> Unit
) {
    // Inject the ViewModel using Koin
    val viewModel: HomeViewModel = koinInject()

    // Remember coroutine scope for managing coroutines
    val coroutineScope = rememberCoroutineScope()

    // Remember the state for lazy list scrolling
    val scrollState = rememberLazyListState()

    // Remember the state for the current NativeAd
    val currentNativeAd = remember { viewModel.currentNativeAd }

    // Collect the HomeState from the ViewModel
    val state = viewModel.homeState.collectAsState().value

    // Remember states for network checking, carousel visibility, and image list visibility
    val isNetworkAvailable = remember { mutableStateOf(false) }
    val isCarouselVisible = remember { mutableStateOf(false) }
    val isImagesListVisible = remember { mutableStateOf(false) }

    // Render the HomeContent Composable with the current state and actions
    HomeContent(
        coroutineScope = coroutineScope,
        imagePagingItems = state.homeImages?.collectAsLazyPagingItems(),
        scrollState = scrollState,
        nativeAd = currentNativeAd.value,
        networkStatus = isNetworkAvailable,
        isLoading = state.isLoading,
        showError = state.errorDialog,
        isCarouselVisible = isCarouselVisible,
        isImagesListVisible = isImagesListVisible,
        categories = state.homeCategories,
        dismissErrorDialog = { viewModel.hideErrorDialog() },
        fetchNativeAd = { viewModel.loadNativeAd() },
        onSettingClick = onSettingClick,
        onLikeClick = onLikeClick,
        onCategoryClick = onCategoryClick,
        onMoreCategoryClick = onMoreCategoryClick,
        onImageClick = onImageClick
    )
}
