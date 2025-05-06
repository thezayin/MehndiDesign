package com.thezayin.homes.presentation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.paging.compose.collectAsLazyPagingItems
import com.thezayin.framework.analytics.events.AnalyticsEvent
import com.thezayin.framework.components.ErrorQueryDialog
import com.thezayin.framework.components.Loading
import com.thezayin.homes.presentation.component.HomeScreenContent
import com.thezayin.homes.presentation.event.HomeEvent
import org.koin.compose.koinInject

/**
 * Composable function that represents the Home screen UI.
 *
 * It manages the display of home categories, images, ads, and various events triggered by user interactions.
 * The function also handles ad loading and tracking analytics for ad impressions and clicks.
 *
 * @param onSettingClick Function callback triggered when the settings button is clicked.
 * @param onLikeClick Function callback triggered when the like button is clicked.
 * @param onCategoryClick Function callback triggered when a category is selected.
 * @param onImageClick Function callback triggered when an image is selected.
 */
@Composable
fun HomeScreen(
    onSettingClick: () -> Unit = {},
    onLikeClick: () -> Unit = {},
    onCategoryClick: () -> Unit,
    onImageClick: () -> Unit = {},
) {
    // Inject ViewModel and observe UI state
    val vm: HomeViewModel = koinInject()
    val state = vm.state.collectAsState().value
    val homeImages = state.homeImages?.collectAsLazyPagingItems()
    val activity = LocalActivity.current as Activity
    vm.analytics.logEvent(AnalyticsEvent.ScreenViewEvent("HomeScreen"))
    // Ad managers and analytics
    val interstitialAdManager = vm.interstitialAdManager
    val rewardedAdManager = vm.rewardedAdManager
    val analytics = vm.analytics

    // Load ads when the screen is initialized
    LaunchedEffect(Unit) {
        interstitialAdManager.loadAd(activity)
        rewardedAdManager.loadAd(activity)
    }

    // Show loading spinner if the screen is in loading state
    if (state.isLoading) {
        Loading()
    }

    // Display error dialog if there is an error
    if (state.errorDialog) {
        ErrorQueryDialog(
            errorMessage = state.errorMessage,
            callback = { vm.handleEvent(HomeEvent.HideErrorDialog) }
        )
    }

    // HomeScreen content
    HomeScreenContent(
        categories = state.homeCategories,
        homeImages = homeImages,
        showBannerAd = state.showBannerAd,
        onImageClick = { image ->

            interstitialAdManager.showAd(
                showAd = state.showImageSelectionAd,
                activity = activity,
                onNext = {
                    vm.handleEvent(HomeEvent.SelectImage(image))
                    onImageClick()
                },
                adImpression = {
                    // Log the image click event for analytics
                    analytics.logEvent(
                        AnalyticsEvent.ImageClickEvent(
                            "UserClickedImage",
                            image.id.toString()
                        )
                    )
                }
            )
        },
        onCategoryClick = { category ->

            rewardedAdManager.showAd(
                showAd = state.showCategorySelectionAd,
                activity = activity,
                onNext = {
                    vm.handleEvent(HomeEvent.SelectCategory(category))
                    onCategoryClick()
                },
                adImpression = {
                    // Log the category click event for analytics
                    analytics.logEvent(
                        AnalyticsEvent.CategoryClickEvent(
                            "UserSelectedCategory",
                            category.id.toString()
                        )
                    )
                },
                onReward = { reward ->
                    // Log reward received event
                    analytics.logEvent(
                        AnalyticsEvent.AdRewardEvent(
                            "UserReceivedAdReward",
                            reward.toString()
                        )
                    )
                }
            )
        },
        onSettingClick = {
            // Log setting button click event for analytics
            analytics.logEvent(AnalyticsEvent.SettingClickEvent("UserClickedSetting"))
            onSettingClick()
        },
        onLikeClick = {

            rewardedAdManager.showAd(
                showAd = state.showCategorySelectionAd,
                activity = activity,
                onReward = { reward ->
                    // Log reward received event
                    analytics.logEvent(
                        AnalyticsEvent.AdRewardEvent(
                            "UserReceivedLikeAdReward",
                            reward.toString()
                        )
                    )
                },
                adImpression = {
                    // Log ad impression for like button
                    analytics.logEvent(AnalyticsEvent.LikeAdImpressionEvent("UserLikedImageAdImpression"))
                },
                onNext = {
                    // Log the like event for analytics
                    analytics.logEvent(AnalyticsEvent.LikeHomeEvent("UserClickOnLikedScreen"))
                    onLikeClick()
                }
            )
        }
    )

    BackHandler {
        activity.finish()
    }
}
