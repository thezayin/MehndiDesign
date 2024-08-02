package com.thezayin.mehndidesign.presentation

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.Lifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.component.TopBar
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.common.dialogs.NetworkDialog
import com.thezayin.common.viewmodel.MainViewModel
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import com.thezayin.like.presentation.LikeViewModel
import com.thezayin.like.presentation.component.LikeImagePreview
import com.thezayin.mehndidesign.R
import com.thezayin.preview.presentation.PreviewViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
@Destination
fun LikedPreviewScreen(navigator: DestinationsNavigator, id: Int, url: String) {
    val viewModel: LikeViewModel = koinInject()
    val mainViewModel: MainViewModel = koinInject()
    val previewViewModel: PreviewViewModel = koinInject()

    val scope = rememberCoroutineScope()
    val activity = LocalContext.current as Activity
    val nativeAd = remember { mainViewModel.nativeAd }
    var checkNetwork by remember { mutableStateOf(false) }

    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    val menuItems = viewModel.bottomMenuList.collectAsState().value.list
    val imageSave = previewViewModel.isQuerySuccess.collectAsState().value.isSuccess

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }
    if (isLoading) {
        LoadingDialog(mainViewModel, showAd = true)
    }

    if (imageSave) {
        Toast.makeText(activity, "Image saved successfully", Toast.LENGTH_SHORT).show()
    }

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                scope.launch {
                    while (this.isActive) {
                        mainViewModel.getNativeAd()
                        delay(20000L)
                    }
                }
            }

            else -> {
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = {
            TopBar(
                modifier = Modifier,
                screenTitle = "Preview",
                onBackClick = { navigator.navigateUp() }
            )

        },
        bottomBar = {
            GoogleNativeAd(
                modifier = Modifier,
                style = GoogleNativeAdStyle.Small,
                nativeAd = nativeAd.value
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LikeImagePreview(url = url, items = menuItems) {
                when (it) {
                    1 -> {
                        previewViewModel.saveImageToStorage(url)
                    }

                    2 -> {
                        Toast.makeText(
                            activity,
                            "Image removed from favorites",

                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.removeImage(id)
                        navigator.navigateUp()
                    }
                }
            }
        }
    }
}