package com.thezayin.framework.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.thezayin.framework.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun LoadingImages() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = colorResource(id = R.color.loading_indicator),
        )
        Text(
            text = stringResource(R.string.loading_fav_img),
            modifier = Modifier
                .padding(top = 5.sdp)
                .align(Alignment.CenterHorizontally),
            color = colorResource(id = R.color.loading_indicator),
            fontSize = 8.ssp
        )
    }
}