package com.thezayin.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thezayin.values.R

/**
 * TopBar is a composable function that displays the top navigation bar with buttons for settings,
 * premium access, and likes.
 *
 * @param settingCallback Callback to be invoked when the settings icon is clicked.
 * @param likeCallback Callback to be invoked when the like icon is clicked.
 * @param onPremiumClick Callback to be invoked when the premium button is clicked.
 */
@Composable
internal fun TopBar(
    settingCallback: () -> Unit,
    likeCallback: () -> Unit,
    onPremiumClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 20.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Settings Icon
            Image(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Settings",
                modifier = Modifier
                    .size(35.dp)
                    .clickable { settingCallback() }
            )

            Spacer(
                modifier = Modifier
                    .width(8.dp)
                    .weight(1f)
            )
            // Row containing Premium Button and Like Icon
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (false) {
                    // Premium Button
                    Button(
                        onClick = { onPremiumClick() },
                        modifier = Modifier.padding(start = 10.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.green_level_1)
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_crown),
                            contentDescription = "Premium",
                            modifier = Modifier.size(25.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
                // Like Icon
                Image(painter = painterResource(id = R.drawable.ic_like_filled),
                    contentDescription = "Like",
                    modifier = Modifier
                        .clickable { likeCallback() }
                        .padding(start = 10.dp)
                        .size(45.dp),
                    contentScale = ContentScale.Fit)

            }
        }
    }
}
