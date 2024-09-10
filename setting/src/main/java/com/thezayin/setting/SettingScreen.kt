package com.thezayin.setting

import androidx.compose.runtime.Composable
import com.thezayin.setting.component.SettingScreenContent

/**
 * Composable function for displaying the settings screen.
 *
 * @param onBackClick Callback function to handle the back button click action.
 */
@Composable
fun SettingScreen(
    onBackClick: () -> Unit
) {
    // Display the content of the setting screen and pass the back button action
    SettingScreenContent(
        onBackClick = onBackClick
    )
}