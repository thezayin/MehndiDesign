package com.thezayin.splash.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.thezayin.framework.components.BannerAd
import com.thezayin.framework.R

/**
 * Composable function that displays the splash screen content.
 * It includes an optional network dialog and the app's logo.
 *
 * @param checkNetwork A mutable state indicating whether the network dialog should be shown.
 */
@Composable
fun SplashScreenContent(
    text: Int
) {
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        bottomBar = {
            Column {
                BottomText(modifier = Modifier, text = text)
                BannerAd()
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ImageHeader(modifier = Modifier.align(Alignment.Center))
        }
    }
}