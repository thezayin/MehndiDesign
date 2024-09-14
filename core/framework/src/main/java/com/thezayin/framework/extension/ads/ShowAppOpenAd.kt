package com.thezayin.framework.extension.ads

import android.app.Activity
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.appopen.AppOpenAd
import com.thezayin.ads.GoogleManager

fun showAppOpenAd(
    activity: Activity,
    googleManager: GoogleManager,
    showAd: Boolean = true,
    callBack: (() -> Unit)? = null,
): AppOpenAd? {
    val ad = googleManager.createAppOpenAd()
    if (!showAd) {
        callBack?.invoke()
        return null
    }

    if (ad == null) {
        Log.e("jejeAppOpenAd", "Ad is null")
        callBack?.invoke()
        return null
    }

    ad.fullScreenContentCallback = object : FullScreenContentCallback() {
        override fun onAdDismissedFullScreenContent() {
            super.onAdDismissedFullScreenContent()
            callBack?.invoke()
        }

        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            super.onAdFailedToShowFullScreenContent(p0)
            callBack?.invoke()
        }
    }
    ad.show(activity)
    return ad
}