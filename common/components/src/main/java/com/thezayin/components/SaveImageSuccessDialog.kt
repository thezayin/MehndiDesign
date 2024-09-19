package com.thezayin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SaveImageSuccessDialog(resetSaveState: () -> Unit) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = { resetSaveState() },
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = { resetSaveState() }) {
                Text("OK")
            }
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.values.R.drawable.ic_download),
                    contentDescription = "Save Success",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Image saved successfully!")
            }
        }
    )
}
