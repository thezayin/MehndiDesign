package com.thezayin.splash

import androidx.lifecycle.ViewModel
import com.thezayin.ads.GoogleManager
import com.thezayin.framework.remote.RemoteConfig

/**
 * ViewModel for the splash screen.
 * It manages interactions with Google Ad Manager and Remote Config during the splash screen phase.
 *
 * @param googleManager Handles Google Ads functionality, such as app open ads.
 * @param remoteConfig Manages configuration settings fetched remotely for dynamic behavior.
 */
class SplashViewModel(
    val googleManager: GoogleManager,
    val remoteConfig: RemoteConfig
) : ViewModel()