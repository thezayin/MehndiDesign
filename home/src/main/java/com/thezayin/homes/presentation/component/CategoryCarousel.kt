package com.thezayin.homes.presentation.component

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thezayin.framework.model.Category

@Composable
fun CategoryCarousel(
    modifier: Modifier = Modifier,
    categories: List<Category>?,
    onCategoryClick: (Category) -> Unit
) {
    categories?.let {
        LazyRow {
            items(categories.size) { category ->
                CategoryItem(
                    modifier = modifier,
                    category = categories[category],
                    onItemClick = { category ->
                        onCategoryClick(category)
                    }
                )
            }
        }
    }
}
