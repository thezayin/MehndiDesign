package com.thezayin.components

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAd(
    showAd: Boolean = true,
) {
    if (!showAd) return
    AndroidView(
        factory = { context ->
            AdView(context).apply {
                // Set the Ad Unit ID for your AdMob banner ad
                adUnitId = "ca-app-pub-3940256099942544/2014213617"

                // Set the ad size to BANNER or any other supported size
               setAdSize(AdSize.BANNER)

                // Create an extra parameter to make the ad collapsible
                val extras = Bundle().apply {
                    putString("collapsible", "bottom")  // Setting the collapsible behavior to bottom
                }

                // Build the ad request with the extra parameters
                val adRequest = AdRequest.Builder()
                    .addNetworkExtrasBundle(com.google.ads.mediation.admob.AdMobAdapter::class.java, extras)
                    .build()

                // Load the ad
                loadAd(adRequest)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    )
}