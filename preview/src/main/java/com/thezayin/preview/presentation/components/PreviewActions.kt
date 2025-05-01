package com.thezayin.preview.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.thezayin.framework.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PreviewActions(
    isLiked: Boolean, onLike: () -> Unit, onShare: () -> Unit, onDownload: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.sdp)
            .padding(horizontal = 15.sdp, vertical = 10.sdp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.preview_action_card)
        ),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(10.sdp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                modifier = Modifier.size(18.sdp),
                onClick = onLike
            ) {
                Icon(
                    modifier = Modifier.size(18.sdp),
                    imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    tint = colorResource(id = R.color.red),
                    contentDescription = stringResource(
                        if (isLiked) R.string.cd_unlike else R.string.cd_like
                    )
                )
            }
            IconButton(
                modifier = Modifier.size(18.sdp),
                onClick = onShare
            ) {
                Icon(
                    modifier = Modifier.size(18.sdp),
                    tint = colorResource(id = R.color.dialog_button_color),
                    imageVector = Icons.Default.Share,
                    contentDescription = stringResource(R.string.cd_share)
                )
            }
            IconButton(
                modifier = Modifier.size(18.sdp),
                onClick = onDownload
            ) {
                Icon(
                    modifier = Modifier.size(18.sdp),
                    tint = colorResource(id = R.color.dialog_button_color),
                    imageVector = Icons.Outlined.Download,
                    contentDescription = stringResource(R.string.cd_download)
                )
            }
        }
    }
}