package com.thezayin.splash.event

sealed class SplashEvent {
    data object LoadSplash : SplashEvent()
}
