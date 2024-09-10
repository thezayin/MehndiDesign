package com.thezayin.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.thezayin.domain.model.PreviewMenu

/**
 * Composable function that displays a horizontal list of preview menu items in a row.
 *
 * @param modifier Modifier to apply to the outer Row container.
 * @param menuItems List of [PreviewMenu] items to display.
 * @param onMenuItemClick Callback function that triggers when a menu item is clicked.
 */
@Composable
internal fun PreviewMenuRow(
    modifier: Modifier,
    menuItems: List<PreviewMenu>,
    onMenuItemClick: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 25.dp, vertical = 40.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))  // Rounded corners for the row
            .background(color = colorResource(id = com.thezayin.values.R.color.transparent)),  // Transparent background
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween  // Space out the menu items evenly
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween  // Space out items horizontally in the row
        ) {
            // Loop through the menu items and display each one
            items(menuItems.size) { index ->
                MenuItem(menuItem = menuItems[index], onMenuItemClick = onMenuItemClick)
            }
        }
    }
}