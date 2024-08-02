package com.thezayin.mehndidesign.common.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import com.thezayin.common.viewmodel.MainViewModel
import com.thezayin.framework.extension.ads.showAppOpenAd
import com.thezayin.mehndidesign.common.theme.MehndiDesignTheme
import com.thezayin.mehndidesign.presentation.NavGraphs
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.googleManager.init()
        setContent {
            MehndiDesignTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }

    override fun onStart() {
        showAppOpenAd(this, viewModel.googleManager)
        super.onStart()
    }
}
