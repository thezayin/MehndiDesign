package com.thezayin.like.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thezayin.entities.LikeImageModel

@Composable
fun LikeImageItem(model:LikeImageModel, onClick: (LikeImageModel) -> Unit) {
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 2.dp)
            .clickable {
                onClick(model)
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
                .data(model.URL)
                .allowHardware(false)
                .build(),
            placeholder = painterResource(id = com.thezayin.core.R.drawable.ic_image_placeholder),
            error = painterResource(id = com.thezayin.core.R.drawable.ic_image_placeholder),
            contentDescription = "holder image",
        )
    }
}