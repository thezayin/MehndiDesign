package com.thezayin.mehndidesign.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ramcosta.composedestinations.DestinationsNavHost
import com.thezayin.mehndidesign.presentation.NavGraphs
import com.thezayin.mehndidesign.presentation.theme.MehndiDesignTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MehndiDesignTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
