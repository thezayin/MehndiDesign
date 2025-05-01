package com.thezayin.category.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thezayin.framework.components.ImageItem
import com.thezayin.framework.model.Image
import ir.kaaveh.sdpcompose.sdp

@Composable
fun CategoryImagesGrid(
    images: List<Image>,
    onClick: (Image) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.padding(8.sdp)
    ) {
        items(images.size) { idx ->
            val img = images[idx]
            ImageItem(
                image = img,
                onClick = onClick,
            )
        }
    }
}
