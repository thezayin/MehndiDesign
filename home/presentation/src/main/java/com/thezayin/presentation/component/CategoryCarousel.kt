package com.thezayin.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.domain.model.CategoriesModel
import com.thezayin.values.R

/**
 * A composable function that displays a horizontal scrolling list of categories.
 *
 * @param modifier Modifier to be applied to the entire component.
 * @param state State for the LazyRow scroll position.
 * @param categories List of categories to be displayed.
 * @param onCategoryClick Callback function to be invoked when a category is clicked.
 */
@Composable
internal fun CategoryCarousel(
    modifier: Modifier = Modifier,
    state: LazyListState,
    categories: List<CategoriesModel>?,
    onCategoryClick: (Int, String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        // Title for the category section
        Text(
            text = "Categories",
            color = colorResource(id = R.color.text_color),
            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        // Horizontal list of categories
        LazyRow(
            state = state
        ) {
            categories?.let { categoryList ->
                items(categoryList, key = { it.id }) { category ->
                    CategoryItem(
                        modifier = modifier,
                        item = category,
                        onItemClick = { id, name ->
                            onCategoryClick(
                                id,
                                name
                            )
                        }
                    )
                }
            }
        }
    }
}
