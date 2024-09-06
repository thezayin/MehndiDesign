package com.thezayin.mehndidesign.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.thezayin.ads.GoogleManager
import com.thezayin.framework.extension.ads.showAppOpenAd
import com.thezayin.mehndidesign.nav.NavHost
import com.thezayin.mehndidesign.theme.MehndiDesignTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val googleManager: GoogleManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        googleManager.init()
        setContent {
            val navController = rememberNavController()
            MehndiDesignTheme {
                NavHost(navController = navController)
            }
        }
    }

    override fun onStart() {
        showAppOpenAd(this, googleManager)
        super.onStart()
    }
}
