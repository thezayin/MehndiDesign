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

@Composable
internal fun FavTopBar(
    modifier: Modifier, onBackClick: () -> Unit, screenTitle: String
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
        Image(
            painter = painterResource(id = com.thezayin.drawable.R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    onBackClick()
                },
        )
        Text(
            text = screenTitle,
            color = colorResource(id = com.thezayin.drawable.R.color.text_color),
            fontSize = 22.sp,
            fontFamily = FontFamily(Font(com.thezayin.drawable.R.font.noto_sans_bold)),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.padding(start = 10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = com.thezayin.drawable.R.color.green_level_1)
                )
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.drawable.R.drawable.ic_crown),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}