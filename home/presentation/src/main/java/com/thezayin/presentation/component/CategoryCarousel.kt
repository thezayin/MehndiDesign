package com.thezayin.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.drawable.R
import com.thezayin.entities.Categories

@Composable
internal fun CategoryCarousel(
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
            color = colorResource(id = R.color.text_color),
            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        LazyRow(
            state = state
        ) {
            items(list.size, key = { list[it].id }) { index ->
                CategoryItem(
                    modifier = modifier,
                    item = list[index],
                    onItemClick = { callback(it) }
                )
            }
        }
    }
}