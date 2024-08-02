package com.thezayin.categories.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.entities.Categories

@Composable
fun HomeCategoryCarousel(
    modifier: Modifier,
    state: LazyListState,
    list: List<Categories>,
    callback: (Categories) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
    ) {
        Text(
            text = "Categories",
            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        LazyRow(
            state = state
        ) {
            items(list.size, key = { list[it].id }) { index ->
                Category(
                    modifier = modifier,
                    item = list[index],
                    onItemClick = { callback(it) }
                )
            }
        }
    }
}