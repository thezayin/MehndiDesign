package com.thezayin.mehndidesign.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.thezayin.ads.GoogleManager
import com.thezayin.framework.config.RemoteConfig
import com.thezayin.framework.extension.ads.showAppOpenAd
import com.thezayin.mehndidesign.navigation.NavHost
import com.thezayin.mehndidesign.theme.MehndiDesignTheme
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : ComponentActivity() {

    private val googleManager: GoogleManager by inject()
    private val remoteConfig: RemoteConfig by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            googleManager.init(this)
            Timber.d("MainActivity", "GoogleManager initialized")
            setContent {
                Timber.d("MainActivity", "Setting Compose content")
                MehndiDesignTheme {
                    val navController = rememberNavController()
                    NavHost(navController = navController)
                }
            }
            Timber.d("MainActivity", "Compose content set successfully")
        } catch (e: Exception) {
            Timber.e(e, "MainActivity", "Exception during onCreate")
            Toast.makeText(this, "Crash occurred: ${e.message}", Toast.LENGTH_LONG).show()
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
