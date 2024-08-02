package com.thezayin.mehndidesign.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.Lifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.categories.presentation.CategoriesViewModel
import com.thezayin.categories.presentation.component.HomeCategoryCarousel
import com.thezayin.common.dialogs.ErrorQueryDialog
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.common.dialogs.NetworkDialog
import com.thezayin.common.viewmodel.MainViewModel
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import com.thezayin.home.presentation.HomeViewModel
import com.thezayin.home.presentation.component.HomeImagesList
import com.thezayin.home.presentation.component.HomeTopBar
import com.thezayin.mehndidesign.R
import com.thezayin.mehndidesign.presentation.destinations.CategoryScreenDestination
import com.thezayin.mehndidesign.presentation.destinations.LikedScreenDestination
import com.thezayin.mehndidesign.presentation.destinations.MoreCategoriesScreenDestination
import com.thezayin.mehndidesign.presentation.destinations.PreviewScreenDestination
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: HomeViewModel = koinInject()
    val mainViewModel: MainViewModel = koinInject()
    val categoryViewModel: CategoriesViewModel = koinInject()

    val state = rememberLazyListState()
    val nativeAd = remember { mainViewModel.nativeAd }
    var checkNetwork by remember { mutableStateOf(false) }

    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    var isError = viewModel.isQueryError.collectAsState().value.isError
    val categoryList = categoryViewModel.homeCategories.collectAsState().value.list
    val getAllImages = viewModel.homeImages.collectAsState().value.list.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (isLoading) {
        LoadingDialog(mainViewModel, showAd = true)
    }

    if (isError) {
        ErrorQueryDialog(
            showDialog = { isError = it },
            callback = { navigator.navigateUp() },
            error = "Unstable internet Connection"
        )
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
            HomeTopBar(
                settingCallback = {},
                likeCallback = {
                    navigator.navigate(
                        LikedScreenDestination
                    )
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
            HomeCategoryCarousel(modifier = Modifier, state = state, list = categoryList) {
                if (it.id == 5) {
                    navigator.navigate(MoreCategoriesScreenDestination)
                } else {
                    navigator.navigate(CategoryScreenDestination(it.id, it.title))
                }

            }
            HomeImagesList(items = getAllImages, modifier = Modifier) { url ->
                navigator.navigate(PreviewScreenDestination(url))
            }
        }
    }
}
