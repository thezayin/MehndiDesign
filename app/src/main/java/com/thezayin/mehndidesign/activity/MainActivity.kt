package com.thezayin.mehndidesign.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.thezayin.ads.GoogleManager
import com.thezayin.framework.config.RemoteConfig
import com.thezayin.framework.extension.ads.showAppOpenAd
import com.thezayin.mehndidesign.navigation.NavHost
import com.thezayin.mehndidesign.theme.MehndiDesignTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val googleManager: GoogleManager by inject()
    private val remoteConfig: RemoteConfig by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        googleManager.init(this)
        setContent {
            MehndiDesignTheme {
                val navController = rememberNavController()
                NavHost(navController = navController)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        showAppOpenAd(
            activity = this@MainActivity,
            googleManager = googleManager,
            showAd = remoteConfig.adConfigs.appOpenAd
        )
    }
}
