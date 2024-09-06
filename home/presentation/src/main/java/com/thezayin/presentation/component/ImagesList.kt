package com.thezayin.presentation.component

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.thezayin.drawable.R
import com.thezayin.entities.ImageModel

@Composable
internal fun ImagesList(
    items: LazyPagingItems<ImageModel>,
    modifier: Modifier,
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = "Trending",
            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = colorResource(id = R.color.text_color)
        )
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = modifier
                .fillMaxSize(),
        ) {
            items(items.itemCount) {
                items[it]?.let { model ->
                    ImageItem(
                        url = model.URL,
                        onClick = onClick
                    )
                }
            }
        }
    }
}