package com.thezayin.category.presentation

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.thezayin.category.presentation.component.CategoryScreenContent
import com.thezayin.category.presentation.event.CategoryEvent
import com.thezayin.framework.analytics.events.AnalyticsEvent
import org.koin.compose.koinInject

@Composable
fun CategoryScreen(
    onBack: () -> Unit,
    onPreview: () -> Unit,
    vm: CategoryViewModel = koinInject()
) {
    val state = vm.state.collectAsState().value
    val adManager = vm.rewardedAdManager
    val activity = LocalActivity.current as Activity

    LaunchedEffect(Unit) {
        adManager.loadAd(activity)
    }
    vm.analytics.logEvent(AnalyticsEvent.ScreenViewEvent("CategoryScreen"))
    CategoryScreenContent(
        state = state,
        onBack = onBack,
        showBannerAd = state.showBannerAd,
        onImageClick = { image ->
            adManager.showAd(
                showAd = state.showImageSelectionAd,
                activity = activity,
                adImpression = {
                    vm.analytics.logEvent(
                        AnalyticsEvent.AdImpressionEvent(
                            "CategoryScreenAd",
                            "UserAdImpression"
                        )
                    )
                },
                onReward = {
                    vm.analytics.logEvent(
                        AnalyticsEvent.AdRewardEvent(
                            "CategoryScreenAd",
                            "UserRewardedAd"
                        )
                    )
                },
                onNext = {
                    vm.onEvent(CategoryEvent.ClickImage(image))
                    onPreview()
                })
        }
    )
}