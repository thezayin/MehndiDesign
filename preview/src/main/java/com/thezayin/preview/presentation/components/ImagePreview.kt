package com.thezayin.preview.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thezayin.entities.PreviewMenu

@Composable
fun ImagePreview(url: String, items: List<PreviewMenu>, callback: (Int) -> Unit) {
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
        BottomMenu(
            modifier = Modifier.align(Alignment.BottomCenter),
            items = items,
            callback = callback
        )
    }
}

@Preview
@Composable
fun ImagePreviewDefault() {
    ImagePreview(
        url = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png",
        items = listOf(
            PreviewMenu(
                id = 1,
                name = "Home",
                icon = com.thezayin.core.R.drawable.ic_home
            ),
            PreviewMenu(
                id = 2,
                name = "Search",
                icon = com.thezayin.core.R.drawable.ic_search
            ),
            PreviewMenu(
                id = 3,
                name = "Profile",
                icon = com.thezayin.core.R.drawable.ic_profile
            )
        )
    ) {}
}