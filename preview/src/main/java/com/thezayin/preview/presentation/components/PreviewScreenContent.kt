package com.thezayin.preview.presentation.components

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.thezayin.framework.R
import com.thezayin.framework.components.TopBar

@Composable
fun PreviewScreenContent(
    isLiked: Boolean,
    imageUrl: String,
    showBannerAd: Boolean,
    onLike: () -> Unit,
    onShare: () -> Unit,
    onDownload: () -> Unit,
    onBackClick: () -> Unit,
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    val transformableState = rememberTransformableState { zoomChange, panChange, _ ->
        scale = (scale * zoomChange).coerceIn(1f, 5f)
        offsetX += panChange.x
        offsetY += panChange.y
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        containerColor = colorResource(R.color.background),
        bottomBar = {
            PreviewBottomBar(
                showAd = showBannerAd,
                isLiked = isLiked,
                onLike = onLike,
                onShare = onShare,
                onDownload = onDownload,

                )
        },
        topBar = {
            TopBar(
                modifier = Modifier,
                onBackClick = {
                    onBackClick()
                },
            )
        },
        content = { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                ImagePreview(
                    imageUrl = imageUrl,
                    scale = scale,
                    offsetX = offsetX,
                    offsetY = offsetY,
                    transformableState = transformableState,
                    onZoomOut = {
                        scale = 1f
                        offsetX = 0f
                        offsetY = 0f
                    }
                )
            }
        }
    )
}