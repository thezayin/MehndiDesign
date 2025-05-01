package com.thezayin.start_up.setting

import androidx.compose.runtime.Composable
import com.thezayin.framework.analytics.events.AnalyticsEvent
import com.thezayin.start_up.setting.component.SettingScreenContent
import org.koin.compose.koinInject

/**
 * Composable function for displaying the settings screen.
 *
 * @param onBackClick Callback function to handle the back button click action.
 */
@Composable
fun SettingScreen(
    onBackClick: () -> Unit,
    vm: SettingViewModel = koinInject()
) {
    vm.analytics.logEvent(AnalyticsEvent.ScreenViewEvent("SettingsScreenView"))

    SettingScreenContent(
        analytics = vm.analytics,
        showBannerAd = vm.remoteConfig.adConfigs.settingBannerAd,
        onBackClick = {
            onBackClick()
        }
    )
}
