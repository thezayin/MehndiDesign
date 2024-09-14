package com.thezayin.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.thezayin.framework.extension.ads.showInterstitialAd
import com.thezayin.framework.extension.ads.showRewardedAd
import com.thezayin.presentation.component.CategoryScreenContent
import org.koin.compose.koinInject

@Composable
fun CategoryScreen(
    onBackClick: () -> Unit, onCategoryClick: (Int, String?) -> Unit
) {
    val viewModel: CategoryViewModel = koinInject()

    val scope = rememberCoroutineScope()
    val nativeAd = remember { viewModel.nativeAd }
    val checkNetwork = remember { mutableStateOf(false) }
    val state = viewModel.categoryListState.collectAsState().value
    val activity = LocalContext.current as Activity
    val googleManager = viewModel.googleManager
    val remoteConfig = viewModel.remoteConfig.adConfigs

    val showBottomAd = remoteConfig.nativeAdOnCategoryScreen
    val showLoadingAd = remoteConfig.nativeAdOnCategoryLoading
    val categoryClickAd = remoteConfig.adOnCategorySelectionScreen
    val switchCategoryAd = remoteConfig.interstitialToRewardedOnCategorySelectionScreen
    val adOnBackPress = remoteConfig.adOnBackPress

    CategoryScreenContent(isError = state.showError,
        isLoading = state.isLoading,
        nativeAd = nativeAd.value,
        showBottomAd = showBottomAd,
        scope = scope,
        showLoadingAd = showLoadingAd,
        categories = state.categories,
        checkNetwork = checkNetwork,
        getNativeAd = { viewModel.getNativeAd() },
        onBackClick = {
            showInterstitialAd(
                activity = activity,
                showAd = adOnBackPress,
                manager = googleManager,
                callBack = { onBackClick() }
            )
        },
        hideErrorDialog = { viewModel.hideErrorDialog() },
        onCategoryClick = { id, title ->
            if (switchCategoryAd) {
                showRewardedAd(context = activity,
                    showAd = categoryClickAd,
                    googleManager = googleManager,
                    callback = { onCategoryClick(id, title) })
            } else {
                showInterstitialAd(activity = activity,
                    showAd = categoryClickAd,
                    manager = googleManager,
                    callBack = { onCategoryClick(id, title) })
            }
        })
}