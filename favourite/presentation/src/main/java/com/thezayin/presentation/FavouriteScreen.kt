package com.thezayin.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.thezayin.dialogs.LoadingDialog
import com.thezayin.dialogs.NetworkDialog
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import com.thezayin.presentation.component.FavTopBar
import com.thezayin.presentation.component.ImageList
import com.thezayin.presentation.component.NoItemPresent
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun FavouriteScreen(
    onBackClick: () -> Unit,
    onImageClick: (String) -> Unit
) {
    val viewModel: FavouriteViewModel = koinInject()

    val scope = rememberCoroutineScope()
    val nativeAd = remember { viewModel.nativeAd }
    val noItemVisible = remember { mutableStateOf(false) }
    var checkNetwork by remember { mutableStateOf(false) }

    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    val list = viewModel.imagesUrl.collectAsState().value.list

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

    LaunchedEffect(list.isEmpty()) {
        noItemVisible.value = list.isEmpty()
    }

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

            else -> {
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = com.thezayin.drawable.R.color.background),
        topBar = {
            FavTopBar(
                modifier = Modifier,
                screenTitle = "My Favorites",
                onBackClick = {
                    onBackClick()
                }
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
            if (list.isEmpty()) {
                AnimatedVisibility(
                    visible = noItemVisible.value,
                    enter = slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
                    ),
                ) {
                    NoItemPresent(Modifier)
                }
            } else {
                ImageList(
                    modifier = Modifier,
                    items = list,
                    callback = { model ->
                        model.URL.let {
                            onImageClick(it)
                        }
                    }
                )
            }
        }
    }
}