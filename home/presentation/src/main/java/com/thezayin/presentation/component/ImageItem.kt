package com.thezayin.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thezayin.values.R

@Composable
internal fun ImageItem(url: String, onClick: (String) -> Unit) {
    val isLoading = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 2.dp)
            .clickable {
                onClick(url)
            }
            .wrapContentHeight()
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
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
            CircularProgressIndicator(
                color = colorResource(id = R.color.black),
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}