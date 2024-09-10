package com.thezayin.splash.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.components.NetworkDialog
import com.thezayin.values.R

/**
 * Composable function that displays the splash screen content.
 * It includes an optional network dialog and the app's logo.
 *
 * @param checkNetwork A mutable state indicating whether the network dialog should be shown.
 */
@Composable
fun SplashScreenContent(
    checkNetwork: MutableState<Boolean>
) {
    // Show network dialog if network connectivity is absent
    if (checkNetwork.value) {
        NetworkDialog(showDialog = { checkNetwork.value = it })
    }

    // Main container for the splash screen content
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(color = colorResource(id = R.color.background))
    ) {
        // Card containing the splash screen logo
        Card(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center),  // Center the card in the box
            shape = RoundedCornerShape(200.dp)  // Rounded circular card
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_second_logo),
                contentDescription = null,  // No description as it's decorative
                contentScale = ContentScale.FillBounds,  // Fill the card bounds
            )
        }

        // Bottom row containing an icon and text
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 25.dp, vertical = 25.dp)
                .wrapContentWidth()
                .height(50.dp),  // Setting height for the row
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Icon inside a card
            Card(
                modifier = Modifier.padding(end = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.text_color)
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_hena),
                    contentDescription = null,  // No description for this decorative icon
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(40.dp)
                )
            }

            // Spacer line between icon and text
            Spacer(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.text_color))
                    .width(1.dp)
                    .height(40.dp)
            )

            // Text next to the icon
            Text(
                text = "Mehndi Designs and Ideas",
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.text_color)
            )
        }
    }
}