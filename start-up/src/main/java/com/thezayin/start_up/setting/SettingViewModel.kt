package com.thezayin.start_up.setting

import androidx.lifecycle.ViewModel
import com.thezayin.framework.analytics.analytics.Analytics
import com.thezayin.framework.config.RemoteConfig

/**
 * ViewModel for handling settings-related logic, including managing Google Ads and Remote Config.
 *
 * @param remoteConfig Manages remote configurations to dynamically control settings and features.
 */
class SettingViewModel(
    val remoteConfig: RemoteConfig,
    val analytics: Analytics
) : ViewModel()