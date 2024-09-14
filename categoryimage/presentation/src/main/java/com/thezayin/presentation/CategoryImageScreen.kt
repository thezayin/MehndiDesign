package com.thezayin.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.collectAsLazyPagingItems
import com.thezayin.framework.extension.ads.showInterstitialAd
import com.thezayin.framework.extension.ads.showRewardedAd
import com.thezayin.presentation.component.CategoryImageContent
import org.koin.compose.koinInject

@Composable
fun CategoryImageScreen(
    id: Int,
    title: String,
    onBackClick: () -> Unit,
    onImageSelection: (String) -> Unit
) {
    val viewModel: CategoryImageViewModel = koinInject()
    val state = viewModel.categoryImages.collectAsState().value

    val scope = rememberCoroutineScope()
    val nativeAd = remember { viewModel.nativeAd }
    val checkNetwork = remember { mutableStateOf(false) }
    val activity = LocalContext.current as Activity

    val googleManager = viewModel.googleManager
    val remoteConfig = viewModel.remoteConfig.adConfigs

    val showBottomAd = remoteConfig.nativeAdOnCategoryImageScreen
    val showLoadingAd = remoteConfig.nativeAdOnCategoryImageLoading
    val adOnBackPress = remoteConfig.adOnBackPress
    val showImageSelectionAd = remoteConfig.adOnCategoryImageSelection
    val switchImageSelectionAd = remoteConfig.interstitialToRewardedOnImageSelection

    CategoryImageContent(
        id = id,
        scope = scope,
        title = title,
        nativeAd = nativeAd.value,
        showBottomAd = showBottomAd,
        showLoadingAd = showLoadingAd,
        isError = state.showError,
        isLoading = state.isLoading,
        errorMessage = state.errorMessage,
        items = state.images?.collectAsLazyPagingItems(),
        onBackClick = {
            showInterstitialAd(
                activity = activity,
                showAd = adOnBackPress,
                manager = googleManager,
                callBack = { onBackClick() }
            )
        },
        checkNetwork = checkNetwork,
        getNativeAd = { viewModel.getNativeAd() },
        hideErrorDialog = { viewModel.hideErrorDialog() },
        fetchRemoteFoot = { viewModel.fetchRemoteFoot() },
        fetchRemoteIndo = { viewModel.fetchRemoteIndo() },
        fetchRemoteTikki = { viewModel.fetchRemoteTikki() },
        fetchRemoteTattoo = { viewModel.fetchRemoteTattoo() },
        fetchRemoteBridal = { viewModel.fetchRemoteBridal() },
        fetchRemoteIndian = { viewModel.fetchRemoteIndian() },
        fetchRemoteArabic = { viewModel.fetchRemoteArabic() },
        fetchRemoteFinger = { viewModel.fetchRemoteFinger() },
        fetchRemoteClassic = { viewModel.fetchRemoteClassic() },
        fetchRemotePakistani = { viewModel.fetchRemotePakistani() },
        fetchRemoteMoroccan = { viewModel.fetchRemoteMoroccan() },
        onImageClick = { image ->
            if (switchImageSelectionAd) {
                showRewardedAd(
                    context = activity,
                    showAd = showImageSelectionAd,
                    googleManager = googleManager,
                    callback = { onImageSelection(image) }
                )
            } else {
                showInterstitialAd(
                    activity = activity,
                    showAd = showImageSelectionAd,
                    manager = googleManager,
                    callBack = { onImageSelection(image) }
                )
            }
        }
    )
}