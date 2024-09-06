package com.thezayin.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.paging.compose.collectAsLazyPagingItems
import com.thezayin.dialogs.ErrorQueryDialog
import com.thezayin.dialogs.LoadingDialog
import com.thezayin.dialogs.NetworkDialog
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import com.thezayin.presentation.component.CategoryCarousel
import com.thezayin.presentation.component.ImagesList
import com.thezayin.presentation.component.TopBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    onCategoryClick: (Int, String?) -> Unit,
    onMoreCategoryClick: () -> Unit,
    onImageClick: (String) -> Unit,
    onLikeClick: () -> Unit,
    onSettingClick: () -> Unit
) {
    val viewModel: HomeViewModel = koinInject()

    val state = rememberLazyListState()
    val nativeAd = remember { viewModel.nativeAd }
    var checkNetwork by remember { mutableStateOf(false) }

    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    var isError = viewModel.isQueryError.collectAsState().value.isError
    val categoryList = viewModel.homeCategories.collectAsState().value.list
    val getAllImages = viewModel.homeImages.collectAsState().value.list.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()

    val isCarouselVisible = remember { mutableStateOf(false) }
    val isImagesListVisible = remember { mutableStateOf(false) }


    LaunchedEffect(isLoading) {
        scope.launch {
            delay(300)
            isCarouselVisible.value = true
            delay(300)
            isImagesListVisible.value = true
        }
    }

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

    if (isError) {
        ErrorQueryDialog(
            showDialog = { isError = it },
            callback = {},
            error = "Unstable internet Connection"
        )
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
            TopBar(
                settingCallback = {
                    onSettingClick()
                },
                likeCallback = {
                    onLikeClick()
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
            AnimatedVisibility(
                visible = isCarouselVisible.value,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
                )
            ) {
                CategoryCarousel(modifier = Modifier, state = state, list = categoryList) {
                    if (it.id == 5) {
                        onMoreCategoryClick()
                    } else {
                        onCategoryClick(it.id, it.title)
                    }

                }
            }
            // Animated ImagesList with delay
            AnimatedVisibility(
                visible = isImagesListVisible.value,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
                )
            ) {

                ImagesList(items = getAllImages, modifier = Modifier) { url ->
                    onImageClick(url)
                }
            }
        }
    }
}