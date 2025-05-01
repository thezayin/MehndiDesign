package com.thezayin.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.thezayin.values.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

/**
 * Composable function for displaying a custom top bar with a back button, screen title, and an action button.
 *
 * @param modifier Modifier to be applied to the top bar.
 * @param onBackClick Callback function to handle the back button click action.
 * @param screenTitle Title to be displayed in the top bar.
 */
@Composable
fun SettingTopBar(
    modifier: Modifier = Modifier,
    screenTitle: String,
    onBackClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.sdp)
            .padding(horizontal = 10.sdp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(20.sdp)
                .clickable {
                    onBackClick()
                },
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            tint = colorResource(R.color.dialog_button_color),
            contentDescription = null
        )
        Text(
            text = screenTitle,
            color = colorResource(id = R.color.text_color),
            fontSize = 12.ssp,
            fontFamily = FontFamily(Font(R.font.noto_sans_bold))
        )
        Spacer(modifier)
    }
}
