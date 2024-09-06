package com.thezayin.presentation

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.thezayin.dialogs.LoadingDialog
import com.thezayin.dialogs.NetworkDialog
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import com.thezayin.presentation.components.ImagePreview
import com.thezayin.presentation.components.TopBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun PreviewScreen(
    url: String,
    onBackClick: () -> Unit
) {
    val viewModel: PreviewViewModel = koinInject()

    val scope = rememberCoroutineScope()
    val activity = LocalContext.current as Activity
    val nativeAd = remember { viewModel.nativeAd }
    var checkNetwork by remember { mutableStateOf(false) }

    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    val previewItem = viewModel.getMenuItems.collectAsState().value.list
    val previewLikedItem = viewModel.getFavouriteMenuItems.collectAsState().value.list
    val getId = viewModel.urlId.collectAsState().value.id
    val imageSave = viewModel.isQuerySuccess.collectAsState().value.isSuccess
    val isImageExist = viewModel.isImageExist.collectAsState().value.isSuccess

    Log.d("PreviewScreen", "isImageExist: $isImageExist")

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }
    if (isLoading) {
        LoadingDialog(
            ad = {
                GoogleNativeAd(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    nativeAd = nativeAd.value,
                    style = GoogleNativeAdStyle.Small
                )
            },
            nativeAd = {
                viewModel.getNativeAd()
            },
            showAd = true
        )
    }

    LaunchedEffect(Unit) {
        viewModel.checkImageExit(url)
    }

    if (imageSave) {
        Toast.makeText(activity, "Image saved successfully", Toast.LENGTH_SHORT).show()
    }

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                scope.launch {
                    while (this.isActive) {
                        viewModel.getNativeAd()
                        delay(2000L)
                    }
                }
            }

            else -> {
            }
        }
    }

    Scaffold(modifier = Modifier
        .statusBarsPadding()
        .navigationBarsPadding(),
        containerColor = colorResource(id = com.thezayin.drawable.R.color.background),
        topBar = {
            TopBar(modifier = Modifier, screenTitle = "Preview", onBackClick = {
                onBackClick()
            })

        },
        bottomBar = {
            GoogleNativeAd(
                modifier = Modifier, style = GoogleNativeAdStyle.Small, nativeAd = nativeAd.value
            )
        }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            ImagePreview(
                url = url, items = if (isImageExist) {
                    previewLikedItem
                } else {
                    previewItem
                }
            ) {
                when (it) {
                    1 -> {

                    }

                    2 -> {
                        if (isImageExist) {
                            getId?.let {
                                viewModel.removeImage(getId)
                                Toast.makeText(
                                    activity, "Image removed from favorites", Toast.LENGTH_SHORT
                                ).show()
                                onBackClick()
                            }
                        } else {
                            viewModel.addImage(url)
                            Toast.makeText(
                                activity, "Image added to your favorites", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}
