package com.thezayin.framework.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thezayin.framework.R
import com.thezayin.framework.model.Image
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ImageItem(image: Image, onClick: (Image) -> Unit) {
    val isLoading = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .padding(vertical = 2.sdp, horizontal = 2.sdp)
            .clickable {
                onClick(image)
            }
            .wrapContentHeight()
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.sdp)),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            model = ImageRequest.Builder(LocalContext.current)
                .data(image.image_url)
                .allowHardware(false)
                .listener(
                    onStart = { isLoading.value = true },
                    onSuccess = { _, _ -> isLoading.value = false },
                    onError = { _, _ -> isLoading.value = false }
                )
                .build(),
            error = painterResource(id = R.drawable.ic_image_placeholder),
            contentDescription = "holder image",
        )
        if (isLoading.value) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.loading_indicator),
                )
                Text(
                    text = stringResource(R.string.loading_img),
                    modifier = Modifier
                        .padding(top = 5.sdp)
                        .align(Alignment.CenterHorizontally),
                    color = colorResource(id = R.color.loading_indicator),
                    fontSize = 8.ssp
                )
            }
        }
    }
}