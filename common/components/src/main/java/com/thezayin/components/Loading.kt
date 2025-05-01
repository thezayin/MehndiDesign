package com.thezayin.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.thezayin.values.R

@Composable
fun Loading() {
    Dialog(onDismissRequest = { }) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.background)
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Please wait, downloading resources for first-time use...",
                        fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                        color = colorResource(id = R.color.text_color),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )

                    CircularProgressIndicator(
                        color = colorResource(id = R.color.loading_indicator),
                        modifier = Modifier.size(48.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}
