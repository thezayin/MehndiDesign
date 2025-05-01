package com.thezayin.framework.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdConfigs(
    @SerialName("adOnResume") val adOnResume: Boolean = true,
    @SerialName("bannerOnboardingScreen") val bannerOnboardingScreen: Boolean = true,
    @SerialName("adOnOnboardingCompleted") val adOnOnboardingCompleted: Boolean = true,
    @SerialName("splashBannerAd") val showSplashBannerAd: Boolean = true,
    @SerialName("adOnSplash") val adOnSplash: Boolean = true,
    @SerialName("homeBannerAd") val homeBannerAd: Boolean = true,
    @SerialName("homeImgSelectionAd") val homeImageSelectionAd: Boolean = true,
    @SerialName("categorySelectionAd") val categorySelectionAd: Boolean = true,
    @SerialName("myLikeSelectionAd") val myLikeSelectionAd: Boolean = true,
    @SerialName("previewBannerAd") val previewBannerAd: Boolean = true,
    @SerialName("imgDownloadAd") val imageDownloadAd: Boolean = true,
    @SerialName("imgShareAd") val imageShareAd: Boolean = true,
    @SerialName("categoryBannerAd") val categoryBannerAd: Boolean = true,
    @SerialName("categoryImgSelectionAd") val categoryImageSelectionAd: Boolean = true,
    @SerialName("favoritesBannerAd") val favoritesBannerAd: Boolean = true,
    @SerialName("favoritesImgSelectionAd") val favoritesImageSelectionAd: Boolean = true,
    @SerialName("settingBannerAd") val settingBannerAd: Boolean = true,
)

val defaultAdConfigs = """
{
  "adOnResume": true,
  "splashBannerAd": true,
  "adOnOnboardingCompleted": true,
  "bannerOnboardingScreen": true,
  "adOnSplash": true,
  "homeBannerAd": true,
  "homeImgSelectionAd": true,
  "categorySelectionAd": true,
  "myLikeSelectionAd": true,
  "previewBannerAd": true,
  "imgDownloadAd": true,
  "imgShareAd": true,
  "categoryBannerAd": true,
  "categoryImgSelectionAd": true,
  "favoritesBannerAd": true,
  "favoritesImgSelectionAd": true,
  "settingBannerAd": true
}
""".trimIndent()