package com.thezayin.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.entities.PreviewMenu

@Composable
internal fun MenuItem(
    item: PreviewMenu,
    callback: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .height(65.dp)
            .width(70.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = com.thezayin.drawable.R.color.semi_black)
        )
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    callback(item.id)
                }
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = "Home",
                modifier = Modifier
                    .size(25.dp),
                contentScale = ContentScale.Inside
            )
            Text(
                text = item.name,
                fontSize = 10.sp,
                color = colorResource(id = com.thezayin.drawable.R.color.white),
                fontFamily = FontFamily(Font(com.thezayin.drawable.R.font.noto_sans_regular))
            )
        }
    }
}

@Composable
@Preview
fun MenuItemPreview() {
    MenuItem(
        item = PreviewMenu(
            id = 1,
            name = "Home",
            icon = com.thezayin.drawable.R.drawable.ic_download
        ),
        callback = {}
    )
}