package com.thezayin.mehndidesign.common.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import com.thezayin.common.viewmodel.MainViewModel
import com.thezayin.mehndidesign.presentation.NavGraphs
import com.thezayin.mehndidesign.presentation.theme.MehndiDesignTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.googleManager.init(this)
        viewModel.googleManager.initOnLastConsent()
        setContent {
            MehndiDesignTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
