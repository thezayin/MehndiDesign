package com.thezayin.preview.presentation

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.thezayin.framework.R
import com.thezayin.framework.analytics.events.AnalyticsEvent
import com.thezayin.preview.presentation.components.PreviewScreenContent
import com.thezayin.preview.presentation.components.SaveSuccessDialog
import com.thezayin.preview.presentation.event.PreviewEvent
import org.koin.compose.koinInject

@Composable
fun PreviewScreen(
    onBack: () -> Unit,
    viewModel: PreviewViewModel = koinInject()
) {
    val state by viewModel.state.collectAsState()
    val ctx = LocalContext.current
    val activity = LocalActivity.current as Activity

    val interstitialAdManager = viewModel.interstitialAdManager
    val rewardedAdManager = viewModel.rewardedAdManager
    viewModel.analytics.logEvent(AnalyticsEvent.ScreenViewEvent("PreviewScreen"))
    LaunchedEffect(Unit) {
        interstitialAdManager.loadAd(activity)
        rewardedAdManager.loadAd(activity)
    }

    // Handle successful save event to display dialog
    if (state.isSaveSuccessDialogVisible) {
        SaveSuccessDialog(
            onDismiss = { viewModel.onEvent(PreviewEvent.ShowSaveSuccessDialog(false)) },
            context = ctx
        )
    }

    // Handle image sharing
    LaunchedEffect(state.shareUri) {
        state.shareUri?.let { uri ->
            Intent(Intent.ACTION_SEND).run {
                type = "image/jpeg"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }.let { chooser ->
                ctx.startActivity(Intent.createChooser(chooser, ctx.getString(R.string.share_via)))
            }
            viewModel.onEvent(PreviewEvent.ResetShareFlag)

            // Log share event to Analytics
            viewModel.analytics.logEvent(AnalyticsEvent.ShareImageEvent("UserSharedImage"))
        }
    }

    // Preview Screen Content
    PreviewScreenContent(
        isLiked = state.isLiked,
        imageUrl = state.imageUrl,
        showBannerAd = state.showBannerAd,
        onLike = { viewModel.onEvent(PreviewEvent.Like) },
        onShare = {
            viewModel.analytics.logEvent(
                AnalyticsEvent.LikeEvent("UserLikedImage")
            )
            // Show interstitial ad before sharing
            interstitialAdManager.showAd(
                showAd = state.showBannerAd,
                activity = activity,
                onNext = {
                    viewModel.onEvent(PreviewEvent.Share)
                },
                adImpression = {
                    // Log the impression of the interstitial ad
                    viewModel.analytics.logEvent(
                        AnalyticsEvent.AdImpressionEvent("InterstitialAd", "UserAdImpression")
                    )
                }
            )
        },
        onDownload = {
            viewModel.analytics.logEvent(
                AnalyticsEvent.DownloadEvent("UserStartedDownload")
            )
            // Show rewarded ad before downloading
            rewardedAdManager.showAd(
                showAd = state.showDownloadAd,
                activity = activity,
                adImpression = {
                    viewModel.analytics.logEvent(
                        AnalyticsEvent.AdImpressionEvent("RewardedAd", "UserAdImpression")
                    )
                },
                onReward = {
                    viewModel.analytics.logEvent(
                        AnalyticsEvent.AdImpressionEvent("RewardedAd", "UserAdReward $it")
                    )
                },
                onNext = {
                    viewModel.onEvent(PreviewEvent.Download)
                }
            )
        },
        onBackClick = onBack
    )
}
