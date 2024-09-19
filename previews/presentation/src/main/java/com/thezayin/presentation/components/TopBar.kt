package com.thezayin.presentation.components

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A custom Top Bar composable that includes a back button, screen title, and an action button.
 *
 * @param modifier Modifier to be applied to the TopBar layout.
 * @param onBackClick Callback function to handle the back button click event.
 * @param screenTitle Title to be displayed in the top bar.
 */
@Composable
internal fun TopBar(
    modifier: Modifier, onBackClick: () -> Unit, screenTitle: String
) {
    // Get the current activity context for displaying Toast messages
    val activityContext = LocalContext.current as Activity

    // Layout for the top bar, including the back button, title, and action button
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 10.dp, end = 10.dp),  // Adds padding to the top and sides
        horizontalArrangement = Arrangement.SpaceBetween,  // Spaces elements across the row
        verticalAlignment = Alignment.CenterVertically  // Aligns elements vertically in the center
    ) {
        // Back Button (represented as an icon image)
        Image(
            painter = painterResource(id = com.thezayin.values.R.drawable.ic_back),
            contentDescription = "Back Button",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    onBackClick()  // Trigger the callback on click
                }
        )

        // Screen Title
        Text(
            text = screenTitle,
            color = colorResource(id = com.thezayin.values.R.color.text_color),
            fontSize = 22.sp,  // Font size for the title text
            fontFamily = FontFamily(Font(com.thezayin.values.R.font.noto_sans_bold))  // Custom font family
        )
        Spacer(modifier)
        if (false) {
            // Action Button (with crown icon)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        // Show a "Coming soon" toast message when clicked
                        Toast.makeText(activityContext, "Coming soon", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.padding(start = 10.dp),  // Padding between elements
                    shape = RoundedCornerShape(10.dp),  // Rounded corners for the button
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = com.thezayin.values.R.color.green_level_1)  // Button background color
                    )
                ) {
                    // Crown Icon within the Button
                    Image(
                        painter = painterResource(id = com.thezayin.values.R.drawable.ic_crown),
                        contentDescription = "Crown Icon",
                        modifier = Modifier.size(25.dp),
                        contentScale = ContentScale.Fit  // Scale the image to fit within the button
                    )
                }
            }
        }
    }
}