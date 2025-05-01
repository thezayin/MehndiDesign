package com.thezayin.favorites.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.thezayin.framework.model.Image
import ir.kaaveh.sdpcompose.sdp
import timber.log.Timber
import java.io.File

@Composable
fun FavoriteItem(image: Image, onClick: (Image) -> Unit) {
    val ctx = LocalContext.current
    val file = File(
        ctx.getExternalFilesDir(null)!!, "liked_images/liked_${image.id}.jpg"
    )

    LaunchedEffect(file) {
        Timber.d("FavoriteItem: file.exists()=${file.exists()} at ${file.absolutePath}")
    }

    Card(
        modifier = Modifier
            .padding(vertical = 2.sdp, horizontal = 2.sdp)
            .clickable { onClick(image) }) {
        if (file.exists()) {
            AsyncImage(
                model = file,
                contentDescription = image.id.toString(),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        } else {
            Text(
                "Missing image!",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.sdp),
                color = Color.Red
            )
        }
    }
}