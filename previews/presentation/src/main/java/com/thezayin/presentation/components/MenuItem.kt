package com.thezayin.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.domain.model.PreviewMenu

/**
 * Composable function to display a menu item in a card layout.
 *
 * @param menuItem The [PreviewMenu] item containing the data for the menu.
 * @param onMenuItemClick Callback function to be invoked when the menu item is clicked.
 */
@Composable
internal fun MenuItem(
    menuItem: PreviewMenu,
    onMenuItemClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .height(65.dp)
            .width(70.dp),
        shape = RoundedCornerShape(10.dp),  // Rounded corners for the card
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = com.thezayin.values.R.color.semi_black)  // Background color for the card
        )
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    onMenuItemClick(menuItem.id)  // Trigger the callback when the item is clicked
                }
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,  // Center content vertically
            horizontalAlignment = Alignment.CenterHorizontally  // Center content horizontally
        ) {
            // Icon for the menu item
            Image(
                painter = painterResource(id = menuItem.icon),
                contentDescription = menuItem.name,  // Accessibility description using the item name
                modifier = Modifier.size(25.dp),  // Set the size of the icon
                contentScale = ContentScale.Inside  // Scale the image to fit inside the available space
            )

            // Name of the menu item
            Text(
                text = menuItem.name,
                fontSize = 10.sp,  // Set the font size for the text
                color = colorResource(id = com.thezayin.values.R.color.white),  // Set the text color
                fontFamily = FontFamily(Font(com.thezayin.values.R.font.noto_sans_regular))  // Set the font family
            )
        }
    }
}