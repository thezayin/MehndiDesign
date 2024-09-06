package com.thezayin.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thezayin.entities.PreviewMenu

@Composable
internal fun ImagePreview(url: String, items: List<PreviewMenu>, callback: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .allowHardware(false)
                .build(),
            placeholder = painterResource(id = com.thezayin.drawable.R.drawable.ic_image_placeholder),
            error = painterResource(id = com.thezayin.drawable.R.drawable.ic_image_placeholder),
            contentDescription = "holder image",
        )
        PreviewMenu(
            modifier = Modifier.align(Alignment.BottomCenter),
            items = items,
            callback = callback
        )
    }
}
