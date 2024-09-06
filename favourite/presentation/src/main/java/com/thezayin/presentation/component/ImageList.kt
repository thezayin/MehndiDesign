package com.thezayin.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thezayin.entities.LikeImageModel

@Composable
internal fun ImageList(
    modifier: Modifier,
    items: List<LikeImageModel>,
    callback: (LikeImageModel) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(top = 25.dp)
            .padding(horizontal = 10.dp),
    ) {
        items(items.size, key = { items[it].id ?: it }) {
            FavImageItem(
                model = items[it],
                onClick = callback
            )
        }
    }
}