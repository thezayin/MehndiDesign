package com.thezayin.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.thezayin.dialogs.LoadingDialog
import com.thezayin.dialogs.NetworkDialog
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import com.thezayin.presentation.component.CategoriesTopBar
import com.thezayin.presentation.component.CategoryList
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun CategoriesListScreen(
    onBackClick: () -> Unit,
    onCategoryClick: (Int, String?) -> Unit
) {
    val viewModel: CategoriesViewModel = koinInject()

    val scope = rememberCoroutineScope()
    val nativeAd = remember { viewModel.nativeAd }
    var checkNetwork by remember { mutableStateOf(false) }

    val isLoading = viewModel.isLoading.collectAsState().value.isLoading
    val categories = viewModel.categories.collectAsState().value.list

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
            CategoriesTopBar(
                modifier = Modifier,
                screenTitle = "Categories",
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
            CategoryList(modifier = Modifier, list = categories) {
                onCategoryClick(it.id, it.title)
            }
        }
    }

}