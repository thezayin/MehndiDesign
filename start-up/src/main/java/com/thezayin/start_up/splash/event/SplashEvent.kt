package com.thezayin.start_up.splash.event

sealed class SplashEvent {
    data object LoadSplash : SplashEvent()
    data class ShouldShowSplashAd(val shouldShow: Boolean) : SplashEvent()
    data class ShouldShowBannerAd(val shouldShow: Boolean) : SplashEvent()
}
