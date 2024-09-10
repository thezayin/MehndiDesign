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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.thezayin.domain.model.CategoryModel
import com.thezayin.values.R

@Composable
internal fun CategoriesItem(
    modifier: Modifier,
    item: CategoryModel,
    onItemClick: (Int, String?) -> Unit
) {
    Box(
        modifier = modifier
            .padding(end = 5.dp)
            .width(200.dp)
            .height(120.dp)
            .clickable {
                onItemClick(
                    item.id,
                    item.title
                )
            }
            .background(color = colorResource(id = R.color.transparent)),
    ) {
        Card(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = // Set the target size for the image
                rememberAsyncImagePainter(ImageRequest.Builder(LocalContext.current).data(
                    data = item.icon // Assuming item.icon is a drawable or URL
                ).apply(block = fun ImageRequest.Builder.() {
                    size(200, 120) // Set the target size for the image
                }).build()
                ),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        Card(
            modifier = Modifier
                .fillMaxSize(),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.blur_background),
            )
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.title!!,
                    color = colorResource(id = R.color.white),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}