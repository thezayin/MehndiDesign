package com.thezayin.ads

import android.app.Activity
import android.content.Context
import android.util.AndroidRuntimeException
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.ads.builders.GoogleAppOpenAdLoader
import com.thezayin.ads.builders.GoogleInterstitialAdLoader
import com.thezayin.ads.builders.GoogleNativeAdLoader
import com.thezayin.ads.builders.GoogleRewardedAdLoader
import com.thezayin.ads.ump.ConsentManager
import com.thezayin.ads.utils.AdUnit
import com.thezayin.ads.utils.isWebViewAvailable
import com.thezayin.framework.analytics.analytics.Analytics
import com.thezayin.framework.analytics.events.AnalyticsEvent
import com.vungle.ads.VunglePrivacySettings
import timber.log.Timber

class GoogleManager(
    private val context: Context,
    private val consentManager: ConsentManager,
    private val analytics: Analytics

) {
    private val debug get() = BuildConfig.DEBUG
    private var googleInterAd: GoogleInterstitialAdLoader? = null
    private var googleAppOpen: GoogleAppOpenAdLoader? = null
    private var googleNativeAd: GoogleNativeAdLoader? = null
    private var googleRewardedAd: GoogleRewardedAdLoader? = null

    private val testDeviceIds: List<String> = listOf(
        AdRequest.DEVICE_ID_EMULATOR, "990C1C4A58DB7FED6AF5D9A33E3DD1FF",//Samsung,
        "65B571F43583ED2ABB211D2965BE3E11"
    )

    fun init(activity: Activity) {
        loadAds()
        setMonetizationAdaptersGDPR()
    }

    private fun setMonetizationAdaptersGDPR() {
        // Vungle
        VunglePrivacySettings.apply {
            setGDPRStatus(true, "1.0.0")
            setCCPAStatus(true)
        }
    }

    fun initOnLastConsent() = consentManager.ifCanRequestAds { loadAds() }

    private fun loadAds() {
        if (!isWebViewAvailable(context)) {
            Timber.e("GoogleManager", "WebView is not available on this device.")
            analytics.logEvent(
                AnalyticsEvent.AdInitializationFailed(
                    event = "AdInitializationFailed",
                    reason = "WebView not available"
                )
            )
            // Optionally, notify the user
            if (context is Activity) {
                Toast.makeText(
                    context,
                    "Ad features are unavailable on this device.",
                    Toast.LENGTH_LONG
                ).show()
            }
            return
        }

        try {
            MobileAds.initialize(context) { initializationStatus ->
                Timber.d(
                    "GoogleManager",
                    "MobileAds initialized with status: $initializationStatus"
                )
            }
            if (debug) {
                MobileAds.setRequestConfiguration(
                    RequestConfiguration.Builder()
                        .setTestDeviceIds(testDeviceIds)
                        .build()
                )
            }

            googleRewardedAd = GoogleRewardedAdLoader(context)
            googleInterAd = GoogleInterstitialAdLoader(context)
            googleAppOpen = GoogleAppOpenAdLoader(context)
            googleNativeAd = GoogleNativeAdLoader(context)

            Timber.d("GoogleManager", "All ad types initialized successfully.")
        } catch (e: AndroidRuntimeException) {
            Timber.e("GoogleManager", "Failed to initialize MobileAds: ${e.message}", e)
            analytics.logEvent(
                AnalyticsEvent.AdInitializationFailed(
                    event = "AdInitializationFailed",
                    reason = e.message ?: "Unknown error"
                )
            )
        } catch (e: Exception) {
            Timber.e(
                "GoogleManager",
                "Unexpected error during MobileAds initialization: ${e.message}",
                e
            )
            analytics.logEvent(
                AnalyticsEvent.AdInitializationFailed(
                    event = "AdInitializationFailed",
                    reason = e.message ?: "Unknown error"
                )
            )
        }
    }


    private fun <T> ((Context, String, Analytics) -> AdBuilder<T>).from(unit: AdUnit) = GoogleAd(
        this(context, unit.resolve(debug), analytics).withAnalytics()
    )

    private fun <T> AdBuilder<T>.withAnalytics() = apply {
        onPaid {
            analytics.logEvent(
                AnalyticsEvent.AdPaidEvent(
                    event = "AdPaid",
                    provider = platform,
                    value = (it.valueMicros / 1000000.0).toString()
                )
            )
        }
    }

    fun createAppOpenAd(
        onAdLoaded: (AppOpenAd) -> Unit,
        onAdLoading: () -> Unit,
        onAdFailed: () -> Unit
    ){
        googleAppOpen?.loadAd(
            onAdLoaded = {
                Timber.d("GoogleManager", "App Open Ad loaded successfully.")
            },
            onAdLoading = {
                Timber.d("GoogleManager", "Loading App Open Ad...")
            },
            onAdFailed = {
                Timber.e("GoogleManager", "Failed to load App Open Ad.")
            }
        )
    }
    fun createInterstitialAd() = null
    fun createNativeAd(): NativeAd? = null
    fun createRewardedAd() = null
}