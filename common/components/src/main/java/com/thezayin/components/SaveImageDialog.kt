package com.thezayin.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SaveImageDialog(isSaving: Boolean, saveMessage: String?) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {},
        confirmButton = {},
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = saveMessage ?: "Saving...")
                Spacer(modifier = Modifier.height(16.dp))
                if (isSaving) {
                    CircularProgressIndicator()  // Show progress bar
                }
            }
        }
    )
}
