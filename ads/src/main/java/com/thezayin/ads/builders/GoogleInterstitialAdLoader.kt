package com.thezayin.ads.builders

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.thezayin.ads.BuildConfig
import com.thezayin.ads.utils.AdUnit

class GoogleInterstitialAdLoader(
    private val context: Context,
) {
    private val debug get() = BuildConfig.DEBUG
    private val adUnitId = AdUnit.interstitial.resolve(debug)

    fun loadAd(
        onAdLoaded: (InterstitialAd) -> Unit,
        onAdLoading: () -> Unit,
        onAdFailed: () -> Unit
    ) {
        onAdLoading()
        InterstitialAd.load(
            context, adUnitId, AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    onAdFailed()
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    onAdLoaded(interstitialAd)
                }
            }
        )
    }
}