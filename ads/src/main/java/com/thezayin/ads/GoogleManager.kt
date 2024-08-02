package com.thezayin.ads

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.rewarded.RewardedAd
import com.thezayin.ads.builders.GoogleAppOpenAdBuilder
import com.thezayin.ads.builders.GoogleInterstitialAdBuilder
import com.thezayin.ads.builders.GoogleNativeAdBuilder
import com.thezayin.ads.builders.GoogleRewardedAdBuilder
import com.thezayin.ads.ump.ConsentManager
import com.thezayin.ads.utils.AdUnit
import timber.log.Timber

class GoogleManager(
    private val context: Context,
    private val consentManager: ConsentManager
) {
    private var googleNative: GoogleAd<NativeAd>? = null
    private var googleInterAd: GoogleAd<InterstitialAd>? = null
    private var googleAppOpen: GoogleAd<AppOpenAd>? = null
    private var googleRewarded: GoogleAd<RewardedAd>? = null
    private val debug get() = BuildConfig.DEBUG

    private val testDeviceIds: List<String> = listOf(
        AdRequest.DEVICE_ID_EMULATOR, "ADF56AE377E44EC02913140C18AA8725",
    )

    fun init() {
        MobileAds.initialize(context)
        if (debug) MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
        )
        googleNative = ::GoogleNativeAdBuilder.from(AdUnit.native)
        googleInterAd = ::GoogleInterstitialAdBuilder.from(AdUnit.interstitial)
        googleAppOpen = ::GoogleAppOpenAdBuilder.from(AdUnit.appOpen)
        googleRewarded = ::GoogleRewardedAdBuilder.from(AdUnit.rewarded)
    }

    private fun <T> ((Context, String) -> AdBuilder<T>).from(unit: AdUnit) = GoogleAd(
        this(context, unit.resolve(debug))
    )

    private fun <T> AdBuilder<T>.withAnalytics(): AdBuilder<T> = apply {
        onPaid {
            Timber.tag("GoogleManager").d("onPaid: %s", it)
        }
    }

    private fun <T : Any?> ifNotSubscribed(block: () -> T?): T? = runCatching {
        return block()
    }.getOrNull()

    fun createRewardedInterstitialAd() = googleRewarded?.get()
    fun createAppOpenAd() = googleAppOpen?.get()
    fun createInterstitialAd() = googleInterAd?.get()
    fun createNativeAd(): NativeAd? = googleNative?.get()
    fun createRewardedAd() = googleRewarded?.get()
}