package com.thezayin.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.domain.model.CategoriesModel
import com.thezayin.values.R

/**
 * Displays a single category item within a card.
 *
 * @param modifier Modifier to be applied to the entire component.
 * @param item The category item to display.
 * @param onItemClick Callback function to be invoked when the item is clicked.
 */
@Composable
internal fun CategoryItem(
    modifier: Modifier = Modifier,
    item: CategoriesModel,
    onItemClick: (Int,String) -> Unit
) {
    Box(
        modifier = modifier
            .padding(end = 8.dp)
            .width(200.dp)
            .height(120.dp)
            .clickable { item.name?.let { onItemClick(item.id, it) } }
            .background(color = colorResource(id = R.color.transparent))
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Image(
                painter = painterResource(id = item.iconResId ?: R.drawable.ic_ps),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Card(
            modifier = Modifier
                .fillMaxSize(),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.blur_background)),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = item.name ?: "No Title",
                    color = colorResource(id = R.color.white),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun CategoryItemPreview() {
    CategoryItem(
        item = CategoriesModel(
            id = 1,
            name = "Category",
            iconResId = R.drawable.ic_ps
        )
    ) { _, _ -> }
}