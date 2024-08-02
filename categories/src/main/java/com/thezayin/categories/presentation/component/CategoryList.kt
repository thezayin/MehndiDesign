package com.thezayin.categories.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thezayin.entities.Categories

@Composable
fun CategoryList(
    modifier: Modifier,
    list: List<Categories>,
    callback: (Categories) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .padding(horizontal = 25.dp, vertical = 40.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(list.size) { item ->
            Category(
                item = list[item],
                onItemClick = { callback(it) },
                modifier = modifier
            )
        }
    }
}