package com.thezayin.preview.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thezayin.framework.components.BannerAd
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PreviewBottomBar(
    showAd: Boolean,
    isLiked: Boolean,
    onLike: () -> Unit,
    onShare: () -> Unit,
    onDownload: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        PreviewActions(
            isLiked = isLiked,
            onLike = onLike,
            onShare = onShare,
            onDownload = onDownload
        )
        Spacer(modifier = Modifier.height(5.sdp))
        BannerAd(showAd)
    }
}