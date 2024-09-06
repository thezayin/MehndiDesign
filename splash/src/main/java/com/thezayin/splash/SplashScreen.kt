package com.thezayin.splash

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.dialogs.NetworkDialog
import com.thezayin.framework.extension.ads.showAppOpenAd
import com.thezayin.framework.extension.functions.checkForInternet
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

@Composable
fun SplashScreen(onNavigate: () -> Unit) {
    val viewModel: SplashViewModel = koinInject()
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    var checkNetwork by remember { mutableStateOf(false) }

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    LaunchedEffect(key1 = Unit) {
        if (!context.checkForInternet()) {
            delay(5000L)
            checkNetwork = true
        } else {
            delay(3000L)
            showAppOpenAd(activity, viewModel.googleManager, true) {
                onNavigate()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(color = colorResource(id = com.thezayin.drawable.R.color.background))
    ) {
        Card(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(200.dp)
        ) {
            Image(
                painter = painterResource(id = com.thezayin.drawable.R.drawable.ic_second_logo),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 25.dp, vertical = 25.dp)
                .wrapContentWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                modifier = Modifier.padding(end = 10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = com.thezayin.drawable.R.color.text_color)
                )
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.drawable.R.drawable.ic_hena),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(
                modifier = Modifier
                    .background(color = colorResource(id = com.thezayin.drawable.R.color.text_color))
                    .width(1.dp)
                    .height(40.dp)
            )

            Text(
                text = "Mehndi Designs and Ideas",
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = com.thezayin.drawable.R.color.text_color)
            )
        }
    }
}