package com.thezayin.preview.presentation.components

import android.widget.Toast
import androidx.compose.foundation.gestures.TransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ZoomOutMap
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.thezayin.framework.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ImagePreview(
    imageUrl: String,
    scale: Float,
    offsetX: Float,
    offsetY: Float,
    transformableState: TransformableState,
    onZoomOut: () -> Unit
) {
    val ctx = LocalContext.current
    val isLoading = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.sdp)
                .transformable(state = transformableState)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                ),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .allowHardware(false)
                    .listener(
                        onStart = { isLoading.value = true },
                        onSuccess = { _, _ -> isLoading.value = false },
                        onError = { _, _ -> isLoading.value = false }
                    )
                    .build(),
                contentDescription = stringResource(R.string.cd_preview_image),
                modifier = Modifier.fillMaxSize(),
                onState = { painterState ->
                    if (painterState is AsyncImagePainter.State.Error) {
                        Toast.makeText(ctx, R.string.toast_failed_load, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            )
        }
        if (isLoading.value) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.loading_indicator),
                    modifier = Modifier.size(40.sdp)
                )
                Text(
                    text = stringResource(R.string.loading_img),
                    modifier = Modifier
                        .padding(top = 10.sdp)
                        .align(Alignment.CenterHorizontally),
                    color = colorResource(id = R.color.loading_indicator),
                    fontSize = 10.ssp
                )
            }
        }
        Card(
            modifier = Modifier
                .padding(14.sdp)
                .size(24.sdp)
                .align(Alignment.BottomEnd),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.preview_action_card)
            ),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(6.sdp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    onClick = {
                        onZoomOut()
                    }) {
                    Icon(
                        tint = colorResource(id = R.color.dialog_button_color),
                        modifier = Modifier.size(18.sdp),
                        imageVector = Icons.Default.ZoomOutMap,
                        contentDescription = stringResource(R.string.cd_reset_zoom)
                    )
                }
            }
        }
    }
}