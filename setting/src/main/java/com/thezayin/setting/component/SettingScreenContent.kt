package com.thezayin.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.thezayin.components.BannerAd
import com.thezayin.values.R

/**
 * Composable function that defines the content layout for the settings screen.
 *
 * @param onBackClick Callback function to handle the back button click action.
 */
@Composable
fun SettingScreenContent(
    onBackClick: () -> Unit,
) {
    val screenTitle = stringResource(id = R.string.settings_title)

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = {
            SettingTopBar(
                modifier = Modifier,
                screenTitle = screenTitle,
                onBackClick = {
                    onBackClick()
                },
            )
        },
        bottomBar = {
            BannerAd()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            SettingOptionsList()
        }
    }
}
