package com.thezayin.homes.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.thezayin.framework.R
import ir.kaaveh.sdpcompose.sdp

/**
 * TopBar is a composable function that displays the top navigation bar with buttons for settings,
 * premium access, and likes.
 *
 * @param settingCallback Callback to be invoked when the settings icon is clicked.
 * @param likeCallback Callback to be invoked when the like icon is clicked.
 * @param onPremiumClick Callback to be invoked when the premium button is clicked.
 */
@Composable
internal fun HomeTopBar(
    settingCallback: () -> Unit, likeCallback: () -> Unit, onPremiumClick: () -> Unit = {}
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
            IconButton(
                onClick = { settingCallback() },
                modifier = Modifier
                    .size(20.sdp)
            ) {
                Icon(
                    imageVector = Icons.Sharp.Menu,
                    tint = colorResource(R.color.icon_color),
                    contentDescription = null
                )
            }

            Spacer(
                modifier = Modifier
                    .width(8.sdp)
                    .weight(1f)
            )
            IconButton(
                modifier = Modifier
                    .size(20.sdp),
                onClick = {
                    likeCallback()
                }
            ) {
                Icon(
                    tint = colorResource(R.color.red),
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Like",
                )
            }
        }
    }
}
