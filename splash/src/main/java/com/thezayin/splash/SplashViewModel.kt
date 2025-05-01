package com.thezayin.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.framework.admob.domain.repository.AppOpenAdManager
import com.thezayin.framework.analytics.analytics.Analytics
import com.thezayin.framework.config.RemoteConfig
import com.thezayin.framework.pref.PrefManager
import com.thezayin.splash.event.SplashEvent
import com.thezayin.splash.state.SplashState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    val remoteConfig: RemoteConfig,
    val analytics: Analytics,
    val admobManager: AppOpenAdManager,
    preferencesManager: PrefManager,
) : ViewModel() {
    private val _state = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> = _state.asStateFlow()

    val isFirstTime = preferencesManager.isFirstTime.value

    init {
        sendEvent(SplashEvent.LoadSplash)
    }

    fun sendEvent(event: SplashEvent) {
        when (event) {
            SplashEvent.LoadSplash -> handleLoadSplash()
        }
    }

    private fun handleLoadSplash() {
        viewModelScope.launch {
            val totalTime = 10000L
            val interval = totalTime / _state.value.splashTexts.size

            for (i in _state.value.splashTexts.indices) {
                _state.update {
                    it.copy(
                        currentSplashText = it.splashTexts[i],
                        currentSplashIndex = i
                    )
                }
                delay(interval)
            }
        }
    }
}