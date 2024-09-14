package com.thezayin.framework.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdConfigs(
    @SerialName("showPremium") val showPremium: Boolean = true,
    @SerialName("init_ads") val initAds: Boolean = true,
    @SerialName("appOpenAd") val appOpenAd: Boolean = false,
    @SerialName("adOnSplashScreen") val adOnSplashScreen: Boolean = false,
    @SerialName("adOnPremiumClick") val adOnPremiumClick: Boolean = false,
    @SerialName("adOnSettingClick") val adOnSettingClick: Boolean = false,
    @SerialName("nativeAdOnHomeScreen") val nativeAdOnHomeScreen: Boolean = false,
    @SerialName("nativeAdOnHomeLoadingDialog") val nativeAdOnHomeLoadingDialog: Boolean = false,
    @SerialName("adOnLikeScreenSelection") val adOnLikeScreenSelection: Boolean = false,
    @SerialName("adOnCategorySelection") val adOnCategorySelection: Boolean = false,
    @SerialName("adOnMoreCategorySelection") val adOnMoreCategorySelection: Boolean = false,
    @SerialName("interstitialToRewardedOnCategorySelection") val interstitialToRewardedOnCategorySelection: Boolean = false,
    @SerialName("interstitialToRewardedOnMoreCategorySelection") val interstitialToRewardedOnMoreCategorySelection: Boolean = false,
    @SerialName("adOnImageSelection") val adOnImageSelection: Boolean = false,
    @SerialName("nativeAdOnSettingScreen") val nativeAdOnSettingScreen: Boolean = false,
    @SerialName("adOnBackPress") val adOnBackPress: Boolean = false,
    @SerialName("likeImageSelection") val likeImageSelection: Boolean = false,
    @SerialName("nativeAdOnLikeScreen") val nativeAdOnLikeScreen: Boolean = false,
    @SerialName("nativeAdOnLikeLoading") val nativeAdOnLikeLoading: Boolean = false,
    @SerialName("nativeAdOnCategoryScreen") val nativeAdOnCategoryScreen: Boolean = false,
    @SerialName("nativeAdOnCategoryLoading") val nativeAdOnCategoryLoading: Boolean = false,
    @SerialName("adOnCategorySelectionScreen") val adOnCategorySelectionScreen: Boolean = false,
    @SerialName("interstitialToRewardedOnCategorySelectionScreen") val interstitialToRewardedOnCategorySelectionScreen: Boolean = false,
    @SerialName("nativeAdOnPreviewScreen") val nativeAdOnPreviewScreen: Boolean = false,
    @SerialName("nativeAdOnPreviewLoading") val nativeAdOnPreviewLoading: Boolean = false,
    @SerialName("adOnImageLike") val adOnImageLike: Boolean = false,
    @SerialName("adOnImageRemove") val adOnImageRemove: Boolean = false,
    @SerialName("interstitialToRewardedOnImageRemove") val interstitialToRewardedOnImageRemove: Boolean = false,
    @SerialName("interstitialToRewardedOnImageLike") val interstitialToRewardedOnImageLike: Boolean = false,
    @SerialName("nativeAdOnCategoryImageScreen") val nativeAdOnCategoryImageScreen: Boolean = false,
    @SerialName("nativeAdOnCategoryImageLoading") val nativeAdOnCategoryImageLoading: Boolean = false,
    @SerialName("adOnCategoryImageSelection") val adOnCategoryImageSelection: Boolean = false,
    @SerialName("interstitialToRewardedOnImageSelection") val interstitialToRewardedOnImageSelection: Boolean = false,

    )

val defaultAdConfigs = """
   {
   "init_ads": true,
   "showPremium": true,
   "appOpenAd": true,
   "adOnSplashScreen": true,
   "adOnPremiumClick": true,
   "adOnSettingClick": true,
   "nativeAdOnHomeLoadingDialog": true,
   "adOnImageSelection": true,
   "adOnBackPress": true,
   "adOnLikeScreenSelection": true,
   "nativeAdOnHomeScreen": true,
   "nativeAdOnResultScreen": true,
   "nativeAdOnResultLoadingDialog": true,
   "nativeAdOnSettingScreen": true,
   "nativeAdOnServerScreen": true,
   "nativeAdOnServerLoadingDialog": true,
   "nativeAdOnWebScreen": true,
   "nativeAdOnWebLoadingDialog": true,
   "nativeAdOnPremiumScreen": true,
   "showServerList": true
}
""".trimIndent()