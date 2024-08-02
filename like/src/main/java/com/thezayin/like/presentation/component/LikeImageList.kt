package com.thezayin.like.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thezayin.entities.LikeImageModel

@Composable
fun LikeImageList(
    modifier: Modifier,
    items: List<LikeImageModel>,
    callback: (LikeImageModel) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize(),
    ) {
        items(items.size, key = { items[it].id ?: it }) {
            LikeImageItem(
                model = items[it],
                onClick = callback
            )
        }
    }
}