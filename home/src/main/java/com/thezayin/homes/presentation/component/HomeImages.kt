package com.thezayin.homes.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.paging.compose.LazyPagingItems
import com.thezayin.framework.model.Image
import com.thezayin.framework.R
import com.thezayin.framework.components.ImageItem
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun HomeImages(
    items: LazyPagingItems<Image>?,
    modifier: Modifier,
    onClick: (Image) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Trending",
            modifier = Modifier.padding(bottom = 8.sdp, top = 8.sdp),
            fontWeight = FontWeight.Bold,
            fontSize = 10.ssp,
            color = colorResource(id = R.color.text_color)
        )

        // Staggered Grid to display images
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),  // Set 2 columns in the grid
            modifier = modifier
                .fillMaxSize(),
        ) {
            // Loop over items in LazyPagingItems
            items?.let { lazyItems ->
                items(lazyItems.itemCount) { index ->
                    val model = lazyItems[index]
                    model?.let {
                        // Create ImageItem for each image model
                        ImageItem(
                            image = it, // Use the URL from the model
                            onClick = onClick
                        )
                    }
                }
            }
        }
    }
}