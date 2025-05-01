package com.thezayin.favorites.presentation

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.thezayin.favorites.presentation.components.FavoritesScreenContent
import com.thezayin.favorites.presentation.event.FavoritesEvent
import com.thezayin.framework.analytics.events.AnalyticsEvent
import org.koin.compose.koinInject

@Composable
fun FavoritesScreen(
    onBackClick: () -> Unit,
    onPreviewClick: () -> Unit,
    vm: FavoritesViewModel = koinInject(),
) {
    // Collecting the state from the ViewModel
    val state by vm.state.collectAsState()

    // Ad manager and activity initialization
    val adManager = vm.adManager
    val activity = LocalActivity.current as Activity

    // Load ads on screen start
    LaunchedEffect(Unit) {
        adManager.loadAd(activity)
    }
    vm.analytics.logEvent(AnalyticsEvent.ScreenViewEvent("FavoritesScreen"))
    // Handle Favorites screen content with analytics tracking
    FavoritesScreenContent(
        isLoading = state.isLoading,
        favorites = state.favorites,
        onBack = onBackClick,
        showBannerAd = state.showBannerAd,
        onItemClick = { image ->
            // Log the image click event for analytics
            vm.analytics.logEvent(
                AnalyticsEvent.FavoriteItemClickEvent(
                    "UserClickedFavoriteImage",
                    image.id.toString()
                )
            )

            // Show ad before navigating to preview
            adManager.showAd(
                showAd = state.showImageSelectionAd,
                activity = activity,
                adImpression = {
                    // Log ad impression event for tracking purposes
                    vm.analytics.logEvent(
                        AnalyticsEvent.AdImpressionEvent(
                            "FavoritesScreenAd",
                            "UserAdImpression"
                        )
                    )
                },
                onReward = {
                    // Log reward received event (if applicable) for analytics
                    vm.analytics.logEvent(
                        AnalyticsEvent.AdRewardEvent(
                            "FavoritesScreenReward",
                            it.toString()
                        )
                    )
                },
                onNext = {
                    // On successful ad display, handle the click and proceed to preview
                    vm.onEvent(FavoritesEvent.Click(image))
                    onPreviewClick()
                }
            )
        }
    )
}
