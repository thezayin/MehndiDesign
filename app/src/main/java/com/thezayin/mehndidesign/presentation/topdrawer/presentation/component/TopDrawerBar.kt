package com.thezayin.mehndidesign.presentation.topdrawer.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.mehndidesign.R

@Composable
fun TopDrawerBar(modifier: Modifier, navigator: DestinationsNavigator, string: String) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    navigator.navigateUp()
                }
        )
        Text(
            text =string,
            fontSize = 28.sp,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 30.dp)
        )
    }
}