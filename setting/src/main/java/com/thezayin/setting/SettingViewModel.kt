package com.thezayin.setting

import androidx.lifecycle.ViewModel
import com.thezayin.framework.config.RemoteConfig

/**
 * ViewModel for handling settings-related logic, including managing Google Ads and Remote Config.
 *
 * @param remoteConfig Manages remote configurations to dynamically control settings and features.
 */
class SettingViewModel(
    val remoteConfig: RemoteConfig
) : ViewModel()