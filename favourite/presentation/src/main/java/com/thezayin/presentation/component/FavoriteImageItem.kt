package com.thezayin.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thezayin.databases.models.LikeImageModel
import com.thezayin.values.R

/**
 * A composable that displays a favorite image item with a loading indicator.
 *
 * @param imageModel The [LikeImageModel] containing image data to be displayed.
 * @param onImageClick A lambda function to handle click events, passing the [LikeImageModel].
 */
@Composable
internal fun FavoriteImageItem(
    imageModel: LikeImageModel,
    onImageClick: (LikeImageModel) -> Unit
) {
    // State to track loading status of the image
    val isImageLoading = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 2.dp)
            .clickable { onImageClick(imageModel) } // Handle click events
            .wrapContentHeight()
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp)), // Rounded corners for the image
        contentAlignment = Alignment.BottomCenter
    ) {
        // Load and display the image asynchronously
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageModel.URL) // Set image URL from the model
                .allowHardware(false) // Disable hardware decoding for the image
                .listener(
                    onStart = { isImageLoading.value = true }, // Show loading indicator when image loading starts
                    onSuccess = { _, _ -> isImageLoading.value = false }, // Hide loading indicator on success
                    onError = { _, _ -> isImageLoading.value = false } // Hide loading indicator on error
                )
                .build(),
            error = painterResource(id = R.drawable.ic_image_placeholder), // Placeholder image on error
            contentDescription = "Image placeholder" // Accessibility description
        )

        // Show loading indicator while the image is loading
        if (isImageLoading.value) {
            CircularProgressIndicator(
                color = colorResource(id = R.color.black), // Color of the loading indicator
                modifier = Modifier.wrapContentSize() // Size of the loading indicator
            )
        }
    }
}