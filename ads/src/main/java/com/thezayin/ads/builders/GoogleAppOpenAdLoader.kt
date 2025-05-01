package com.thezayin.ads.builders

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback
import com.thezayin.ads.BuildConfig
import com.thezayin.ads.utils.AdUnit

class GoogleAppOpenAdLoader(
    private val context: Context,
) {
    private val debug get() = BuildConfig.DEBUG
    private val adUnitId = AdUnit.appOpen.resolve(debug)

    fun loadAd(
        onAdLoaded: (AppOpenAd) -> Unit,
        onAdLoading: () -> Unit,
        onAdFailed: () -> Unit
    ) {
        onAdLoading()
        AppOpenAd.load(
            context, adUnitId, AdRequest.Builder().build(),
            object : AppOpenAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    onAdFailed()
                }

                override fun onAdLoaded(appOpenAd: AppOpenAd) {
                    onAdLoaded(appOpenAd)
                }
            }
        )
    }
}