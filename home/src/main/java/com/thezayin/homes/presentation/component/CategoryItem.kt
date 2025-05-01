package com.thezayin.homes.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thezayin.framework.R
import com.thezayin.framework.model.Category
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * Displays a single category item within a card.
 *
 * @param modifier Modifier to be applied to the entire component.
 * @param onItemClick Callback function to be invoked when the item is clicked.
 */
@Composable
internal fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    onItemClick: (Category) -> Unit
) {
    val isLoading = remember { mutableStateOf(true) }
    Box(
        modifier = modifier
            .width(120.sdp)
            .height(80.sdp)
            .clip(RoundedCornerShape(8.sdp))
            .clickable { onItemClick(category) }
            .background(color = colorResource(id = R.color.transparent))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(8.sdp)),
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                model = ImageRequest.Builder(LocalContext.current)
                    .data(category.imageurl)
                    .allowHardware(false)
                    .listener(
                        onStart = { isLoading.value = true },
                        onSuccess = { _, _ -> isLoading.value = false },
                        onError = { _, _ -> isLoading.value = false }
                    )
                    .build(),
                error = painterResource(id = R.drawable.ic_image_placeholder),
                contentDescription = "holder image",
            )
            if (isLoading.value) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.loading_indicator),
                    modifier = Modifier.wrapContentSize()
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(8.sdp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.blur_background)),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = category.name,
                    color = colorResource(id = R.color.white),
                    fontSize = 10.ssp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(4.sdp)
                )
            }
        }
    }
}
