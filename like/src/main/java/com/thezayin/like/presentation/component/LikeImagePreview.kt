package com.thezayin.like.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thezayin.entities.PreviewMenu

@Composable
fun LikeImagePreview(url: String, items: List<PreviewMenu>, callback: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Inside,
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .allowHardware(false)
                .build(),
            placeholder = painterResource(id = com.thezayin.core.R.drawable.ic_image_placeholder),
            error = painterResource(id = com.thezayin.core.R.drawable.ic_image_placeholder),
            contentDescription = "holder image",
        )
        LikeBottomMenu(
            modifier = Modifier.align(Alignment.BottomCenter),
            items = items,
            callback = callback
        )
    }
}
