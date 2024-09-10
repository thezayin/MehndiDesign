package com.thezayin.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thezayin.databases.models.LikeImageModel

/**
 * A composable function that displays a list of images in a staggered grid layout.
 *
 * @param modifier Modifier to apply to the grid.
 * @param items List of images to display.
 * @param onImageClick Callback function to handle image item clicks.
 */
@Composable
internal fun ImageList(
    modifier: Modifier = Modifier,
    items: List<LikeImageModel>,
    onImageClick: (LikeImageModel) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(top = 25.dp)
            .padding(horizontal = 10.dp)
    ) {
        items(items.size, key = { items[it].id ?: it }) { index ->
            FavoriteImageItem(
                imageModel = items[index],
                onImageClick = onImageClick
            )
        }
    }
}
