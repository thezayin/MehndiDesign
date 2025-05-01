package com.thezayin.start_up.setting.di

import com.thezayin.start_up.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Koin module for providing dependencies related to the settings screen.
 * Registers the [SettingViewModel] to be injected wherever required.
 */
val settingModule = module {
    viewModelOf(::SettingViewModel)
}
