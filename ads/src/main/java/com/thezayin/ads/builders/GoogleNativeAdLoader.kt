package com.thezayin.ads.builders

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.thezayin.ads.BuildConfig
import com.thezayin.ads.utils.AdUnit

class GoogleNativeAdLoader(
    private val context: Context
) {
    private val debug get() = BuildConfig.DEBUG
    private val adUnitId = AdUnit.native.resolve(debug)

    fun loadNativeAd(
        onAdLoaded: (NativeAd?) -> Unit,
    ) {
        val adLoader = AdLoader.Builder(context, adUnitId)
            .forNativeAd { ad ->
                onAdLoaded(ad)
            }
            .withNativeAdOptions(NativeAdOptions.Builder().build())
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(error: LoadAdError) {
                    super.onAdFailedToLoad(error)
                    onAdLoaded(null)
                }
            })
            .build()
        adLoader.loadAd(AdRequest.Builder().build())
    }
}