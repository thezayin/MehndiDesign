package com.thezayin.common.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import com.thezayin.common.viewmodel.MainViewModel
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@Composable
fun LoadingDialog(viewModel: MainViewModel, showAd: Boolean) {
    val nativeAd = remember { viewModel.nativeAd }
    val scope = rememberCoroutineScope()

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                scope.launch {
                    while (this.isActive) {
                        viewModel.getNativeAd()
                        delay(20000L)
                    }
                }
            }

            else -> {}
        }
    }
    Dialog(onDismissRequest = { }) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = com.thezayin.core.R.color.white)
            ),
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Please wait....",
                        fontFamily = FontFamily(Font(com.thezayin.core.R.font.noto_sans_bold)),
                        color = colorResource(id = com.thezayin.core.R.color.black),
                        fontSize = 22.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 60.dp)
                            .padding(bottom = 20.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = colorResource(id = com.thezayin.core.R.color.black))
                    }
                    if (showAd) {
                        GoogleNativeAd(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .fillMaxWidth(),
                            nativeAd = nativeAd.value,
                            style = GoogleNativeAdStyle.Small
                        )
                    }
                }
            }
        }
    }
}