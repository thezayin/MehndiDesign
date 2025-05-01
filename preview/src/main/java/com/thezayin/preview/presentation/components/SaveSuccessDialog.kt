package com.thezayin.preview.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thezayin.framework.R
import ir.kaaveh.sdpcompose.sdp

/**
 * Bottom sheet dialog shown after a successful save operation.
 *
 * @param onDismiss Callback to dismiss the bottom sheet.
 * @param context The Android context to handle sharing.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveSuccessDialog(
    onDismiss: () -> Unit,
    context: Context
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = colorResource(R.color.card_background),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Top Cross Button
            IconButton(
                onClick = { onDismiss() },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Close",
                    tint = Color.White
                )
            }

            // Success Icon and Message
            Icon(
                painter = painterResource(R.drawable.ic_success),
                contentDescription = "Save Successful",
                modifier = Modifier.size(54.sdp),
                tint = colorResource(R.color.dialog_button_color)
            )
            Text(
                text = "Saved Successfully!",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(R.color.white)
            )
            Spacer(modifier = Modifier.height(16.sdp))
        }
    }
}