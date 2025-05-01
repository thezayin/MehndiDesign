package com.thezayin.ads.builders

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.thezayin.ads.BuildConfig
import com.thezayin.ads.utils.AdUnit

class GoogleRewardedAdLoader(
    private val context: Context,
) {
    private val debug get() = BuildConfig.DEBUG
    private val adUnitId = AdUnit.rewarded.resolve(debug)

    fun loadAd(
        onAdLoaded: (RewardedAd) -> Unit,
        onAdLoading: () -> Unit,
        onAdFailed: () -> Unit
    ) {
        onAdLoading()
        RewardedAd.load(
            context, adUnitId, AdRequest.Builder().build(),
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    onAdFailed()
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    onAdLoaded(rewardedAd)
                }
            }
        )
    }
}