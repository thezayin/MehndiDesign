package com.thezayin.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thezayin.entities.Categories
import kotlinx.coroutines.delay

@Composable
internal fun CategoryList(
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
            val isVisible = remember { mutableStateOf(false) }
            LaunchedEffect(Unit) {
                delay(100 * item.toLong()) // Delays the appearance of each item
                isVisible.value = true
            }
            AnimatedVisibility(
                visible = isVisible.value,
                enter = slideInVertically(
                    initialOffsetY = { it }, // Slides the item in from below
                    animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
                ),
            ) {
                CategoriesItem(
                    item = list[item],
                    onItemClick = { callback(it) },
                    modifier = modifier
                )
            }
        }
    }
}