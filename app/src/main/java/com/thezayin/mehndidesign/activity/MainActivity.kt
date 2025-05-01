package com.thezayin.mehndidesign.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.MobileAds
import com.thezayin.framework.admob.domain.repository.AppOpenAdManager
import com.thezayin.framework.analytics.analytics.Analytics
import com.thezayin.framework.analytics.events.AnalyticsEvent
import com.thezayin.framework.config.RemoteConfig
import com.thezayin.framework.pref.PrefManager
import com.thezayin.framework.remote.SupabaseApiClient
import com.thezayin.mehndidesign.navigation.NavHost
import com.thezayin.mehndidesign.theme.MehndiDesignTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val remoteConfig: RemoteConfig by inject()
    private val adManager: AppOpenAdManager by inject()
    private val prefManager: PrefManager by inject()
    private val supabaseApiClient: SupabaseApiClient by inject()
    private val analytics: Analytics by inject()
    var isAppFirstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        loadAd()
        try {
            fetchData()
            setContent {
                MehndiDesignTheme {
                    val navController = rememberNavController()
                    NavHost(navController = navController)
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Crash occurred: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }


    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val categories = prefManager.getCategories()
                val fetchedImages = supabaseApiClient.fetchImages()
                prefManager.saveImages(fetchedImages)

                if (categories.isEmpty()) {
                    val fetchedCategories = supabaseApiClient.fetchCategories()
                    prefManager.saveCategories(fetchedCategories)
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(
                        this@MainActivity, "Error fetching data: ${e.message}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun loadAd(){
        adManager.loadAd(this)
    }

    override fun onStart() {
        super.onStart()
        if (isAppFirstTime) {
            isAppFirstTime = false
            return
        }
        adManager.showAd(
            activity = this,
            showAd = remoteConfig.adConfigs.adOnResume,
            onNext = {},
            adImpression = {
                analytics.logEvent(
                    AnalyticsEvent.AdImpressionEvent(
                        adProvider = "adMob",
                        adType = "AppOpenAd",
                    )
                )
            }
        )
    }
}
