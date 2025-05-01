package com.thezayin.splash.state

import  com.thezayin.framework.R

data class SplashState(
    val isLoading: Boolean = true,
    val currentSplashText: Int = R.string.loading_settings,
    val splashTexts: List<Int> = listOf(
        R.string.loading_settings, R.string.finishing_up, R.string.almost_there
    ),
    val currentSplashIndex: Int = 0,
)