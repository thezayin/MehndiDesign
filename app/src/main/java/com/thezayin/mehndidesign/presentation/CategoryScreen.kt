package com.thezayin.mehndidesign.presentation

import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.Lifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.categories.presentation.CategoriesViewModel
import com.thezayin.categories.presentation.component.CategoryImageList
import com.thezayin.common.component.TopBar
import com.thezayin.common.dialogs.LoadingDialog
import com.thezayin.common.dialogs.NetworkDialog
import com.thezayin.common.viewmodel.MainViewModel
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import com.thezayin.mehndidesign.R
import com.thezayin.mehndidesign.presentation.destinations.PreviewScreenDestination
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
@Destination
fun CategoryScreen(navigator: DestinationsNavigator, id: Int, title: String?) {
    val mainViewModel: MainViewModel = koinInject()
    val viewModel: CategoriesViewModel = koinInject()

    val imagesUrl = viewModel.imagesUrl.collectAsState().value.list.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()
    val nativeAd = remember { mainViewModel.nativeAd }
    var checkNetwork by remember { mutableStateOf(false) }

    val isLoading = viewModel.isLoading.collectAsState().value.isLoading

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (isLoading) {
        LoadingDialog(mainViewModel, showAd = true)
    }

    LaunchedEffect(key1 = Unit) {
        when (id) {
            1 -> viewModel.fetchRemoteBridal()
            2 -> viewModel.fetchRemoteIndian()
            3 -> viewModel.fetchRemoteArabic()
            4 -> viewModel.fetchRemotePakistani()
            5 -> viewModel.fetchRemoteMoroccan()
            6 -> viewModel.fetchRemoteClassic()
            7 -> viewModel.fetchRemoteFinger()
            8 -> viewModel.fetchRemoteFoot()
            9 -> viewModel.fetchRemoteTikki()
            10 -> viewModel.fetchRemoteIndo()
            11 -> viewModel.fetchRemoteTattoo()
        }
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
                screenTitle = title ?: "Category",
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
            CategoryImageList(items = imagesUrl, modifier = Modifier) {it ->
                navigator.navigate(PreviewScreenDestination(it))
            }
        }
    }
}