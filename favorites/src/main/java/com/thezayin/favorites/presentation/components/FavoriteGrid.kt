package com.thezayin.favorites.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thezayin.framework.model.Image
import ir.kaaveh.sdpcompose.sdp

@Composable
fun FavoriteGrid(
    favorites: List<Image>,
    onItemClick: (Image) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 10.sdp, vertical = 10.sdp
            ),
        columns = StaggeredGridCells.Fixed(2),
    ) {
        items(favorites.size) { idx ->
            val img = favorites[idx]
            FavoriteItem(img, onClick = onItemClick)
        }
    }
}
