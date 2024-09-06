package com.thezayin.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.drawable.R

@Composable
fun NoItemPresent(modifier: Modifier) {
    Column(
        modifier = Modifier
            .padding(horizontal = 25.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.card_background)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "No item present",
                    color = colorResource(id = R.color.text_color),
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun NoItemPresent() {
    NoItemPresent(Modifier)
}