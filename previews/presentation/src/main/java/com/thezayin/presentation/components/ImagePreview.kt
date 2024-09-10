package com.thezayin.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thezayin.domain.model.PreviewMenu

/**
 * Composable function to display an image preview with a set of menu options at the bottom.
 *
 * @param imageUrl The URL of the image to be displayed.
 * @param menuItems A list of [PreviewMenu] items to be shown in a menu below the image.
 * @param onMenuItemClick Callback function to handle menu item clicks.
 */
@Composable
internal fun ImagePreview(
    imageUrl: String,
    menuItems: List<PreviewMenu>,
    onMenuItemClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize()
    ) {
        // Display the image using Coil's AsyncImage composable
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,  // Fit the image within the available space
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .allowHardware(false)
                .build(),
            placeholder = painterResource(id = com.thezayin.values.R.drawable.ic_image_placeholder),  // Placeholder image while loading
            error = painterResource(id = com.thezayin.values.R.drawable.ic_image_placeholder),  // Error image if loading fails
            contentDescription = "Preview image"  // Accessibility description
        )

        // Display the menu items at the bottom of the image
        PreviewMenuRow(
            modifier = Modifier.align(Alignment.BottomCenter),  // Align menu at the bottom center
            menuItems = menuItems,
            onMenuItemClick = onMenuItemClick  // Pass the menu item click callback
        )
    }
}