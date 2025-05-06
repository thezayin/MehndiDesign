package com.thezayin.framework.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdConfigs(
    @SerialName("adOnResume") val adOnResume: Boolean = false,
    @SerialName("bannerOnboardingScreen") val bannerOnboardingScreen: Boolean = false,
    @SerialName("adOnOnboardingCompleted") val adOnOnboardingCompleted: Boolean = false,
    @SerialName("splashBannerAd") val showSplashBannerAd: Boolean = false,
    @SerialName("adOnSplash") val adOnSplash: Boolean = false,
    @SerialName("homeBannerAd") val homeBannerAd: Boolean = false,
    @SerialName("homeImgSelectionAd") val homeImageSelectionAd: Boolean = false,
    @SerialName("categorySelectionAd") val categorySelectionAd: Boolean = false,
    @SerialName("myLikeSelectionAd") val myLikeSelectionAd: Boolean = false,
    @SerialName("previewBannerAd") val previewBannerAd: Boolean = false,
    @SerialName("imgDownloadAd") val imageDownloadAd: Boolean = false,
    @SerialName("imgShareAd") val imageShareAd: Boolean = false,
    @SerialName("categoryBannerAd") val categoryBannerAd: Boolean = false,
    @SerialName("categoryImgSelectionAd") val categoryImageSelectionAd: Boolean = false,
    @SerialName("favoritesBannerAd") val favoritesBannerAd: Boolean = false,
    @SerialName("favoritesImgSelectionAd") val favoritesImageSelectionAd: Boolean = false,
    @SerialName("settingBannerAd") val settingBannerAd: Boolean = false,
)

val defaultAdConfigs = """
{
  "adOnResume": false,
  "splashBannerAd": false,
  "adOnOnboardingCompleted": false,
  "bannerOnboardingScreen": false,
  "adOnSplash": false,
  "homeBannerAd": false,
  "homeImgSelectionAd": false,
  "categorySelectionAd": false,
  "myLikeSelectionAd": false,
  "previewBannerAd": false,
  "imgDownloadAd": false,
  "imgShareAd": false,
  "categoryBannerAd": false,
  "categoryImgSelectionAd": false,
  "favoritesBannerAd": false,
  "favoritesImgSelectionAd": false,
  "settingBannerAd": false
}
""".trimIndent()