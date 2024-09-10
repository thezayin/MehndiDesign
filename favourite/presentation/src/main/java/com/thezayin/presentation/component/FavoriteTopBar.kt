package com.thezayin.presentation.component

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
 * A composable function that displays a top bar with a back button, screen title, and an action button.
 *
 * @param modifier Modifier to apply to the top bar.
 * @param onBackClick Lambda function to handle back button clicks.
 * @param screenTitle The title text to display in the center of the top bar.
 */
@Composable
internal fun FavoriteTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    screenTitle: String
) {
    val context = LocalContext.current as Activity

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back button
        Image(
            painter = painterResource(id = com.thezayin.values.R.drawable.ic_back),
            contentDescription = "Back",
            modifier = Modifier
                .size(30.dp)
                .clickable { onBackClick() } // Handle back button click
        )

        // Screen title
        Text(
            text = screenTitle,
            color = colorResource(id = com.thezayin.values.R.color.text_color),
            fontSize = 22.sp,
            fontFamily = FontFamily(Font(com.thezayin.values.R.font.noto_sans_bold)),
        )

        // Action button
        Button(
            onClick = {
                Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .padding(start = 10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = com.thezayin.values.R.color.green_level_1)
            )
        ) {
            Image(
                painter = painterResource(id = com.thezayin.values.R.drawable.ic_crown),
                contentDescription = "Crown Icon",
                modifier = Modifier.size(25.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
