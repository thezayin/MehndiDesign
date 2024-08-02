package com.thezayin.categories.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.thezayin.common.component.ImageItem

@Composable
fun CategoryImageList(
    items: LazyPagingItems<String>,
    modifier: Modifier,
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 25.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Trending",
            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = modifier
                .fillMaxSize(),
        ) {
            items(items.itemCount, key = { items[it] ?: it }) {
                items[it]?.let { model -> ImageItem(url = model, onClick = onClick) }
            }
        }
    }
}