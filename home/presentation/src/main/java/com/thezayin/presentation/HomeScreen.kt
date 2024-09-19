package com.thezayin.presentation

import android.app.Activity
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.collectAsLazyPagingItems
import com.thezayin.framework.extension.ads.showInterstitialAd
import com.thezayin.framework.extension.ads.showRewardedAd
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


    LaunchedEffect(Unit) { viewModel.loadImages() }

    // Remember coroutine scope for managing coroutines
    val coroutineScope = rememberCoroutineScope()

    val activity = LocalContext.current as Activity

    // Remember the state for lazy list scrolling
    val scrollState = rememberLazyListState()

    // Remember the state for the current NativeAd
    val currentNativeAd = remember { viewModel.currentNativeAd }

    // Collect the HomeState from the ViewModel
    val state = viewModel.homeState.collectAsState().value

    val showLoadingAd = viewModel.remoteConfig.adConfigs.nativeAdOnHomeLoadingDialog
    val showBottomAd = viewModel.remoteConfig.adConfigs.nativeAdOnHomeScreen
    val showSettingAd = viewModel.remoteConfig.adConfigs.adOnSettingClick
    val showLikeAd = viewModel.remoteConfig.adConfigs.adOnLikeScreenSelection
    val showCategoryAd = viewModel.remoteConfig.adConfigs.adOnCategorySelection
    val showMoreCategoryAd = viewModel.remoteConfig.adConfigs.adOnMoreCategorySelection
    val switchCategoryAd =
        viewModel.remoteConfig.adConfigs.interstitialToRewardedOnCategorySelection
    val switchMoreCategoryAd =
        viewModel.remoteConfig.adConfigs.interstitialToRewardedOnMoreCategorySelection
    // Remember states for network checking, carousel visibility, and image list visibility
    val isNetworkAvailable = remember { mutableStateOf(false) }
    val isCarouselVisible = remember { mutableStateOf(false) }
    val isImagesListVisible = remember { mutableStateOf(false) }

    // Render the HomeContent Composable with the current state and actions
    HomeContent(
        showLoadingAd = showLoadingAd,
        showBottomAd = showBottomAd,
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
        onSettingClick = {
            showInterstitialAd(
                activity = activity,
                showAd = showSettingAd,
                manager = viewModel.googleManager
            ) { onSettingClick() }
        },
        onLikeClick = {
            showInterstitialAd(
                activity = activity,
                showAd = showLikeAd,
                manager = viewModel.googleManager
            ) { onLikeClick() }
        },
        onCategoryClick = { id, url ->
            if (switchCategoryAd) {
                showRewardedAd(
                    context = activity,
                    showAd = showCategoryAd,
                    googleManager = viewModel.googleManager
                ) { onCategoryClick(id, url) }
            } else {
                showInterstitialAd(
                    activity = activity,
                    showAd = showCategoryAd,
                    manager = viewModel.googleManager
                ) { onCategoryClick(id, url) }
            }
        },
        onMoreCategoryClick = {
            if (switchMoreCategoryAd) {
                showRewardedAd(
                    context = activity,
                    showAd = showMoreCategoryAd,
                    googleManager = viewModel.googleManager
                ) {
                    onMoreCategoryClick()
                }
            } else {
                showInterstitialAd(
                    activity = activity,
                    showAd = showMoreCategoryAd,
                    manager = viewModel.googleManager
                ) { onMoreCategoryClick() }
            }
        },
        onImageClick = { image ->
            showRewardedAd(
                context = activity,
                showAd = viewModel.remoteConfig.adConfigs.adOnImageSelection,
                googleManager = viewModel.googleManager
            ) { onImageClick(image) }
        })
}
