package com.thezayin.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.components.ErrorQueryDialog
import com.thezayin.components.LoadingDialog
import com.thezayin.components.NetworkDialog
import com.thezayin.domain.model.CategoryModel
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@Composable
fun CategoryScreenContent(
    isError: Boolean,
    isLoading: Boolean,
    nativeAd: NativeAd?,
    scope: CoroutineScope,
    showBottomAd: Boolean,
    showLoadingAd:Boolean,
    categories: List<CategoryModel>?,
    checkNetwork: MutableState<Boolean>,
    getNativeAd: () -> Unit,
    onBackClick: () -> Unit,
    hideErrorDialog: () -> Unit,
    onCategoryClick: (Int, String?) -> Unit
) {
    if (isLoading) {
        LoadingDialog(
            ad = {
                GoogleNativeAd(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    nativeAd = nativeAd,
                    style = GoogleNativeAdStyle.Small
                )
            },
            nativeAd = {
                getNativeAd()
            },
            showAd = showLoadingAd
        )
    }

    if (isError) {
        ErrorQueryDialog(
            showDialog = { hideErrorDialog() },
            callback = {},
            error = "Unstable internet Connection"
        )
    }

    if (checkNetwork.value) {
        NetworkDialog(showDialog = { checkNetwork.value = it })
    }

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                scope.launch {
                    while (this.isActive) {
                        getNativeAd()
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
        containerColor = colorResource(id = com.thezayin.values.R.color.background),
        topBar = {
            CategoryTopBar(
                modifier = Modifier,
                screenTitle = "Categories",
                onBackClick = {
                    onBackClick()
                }
            )

        },
        bottomBar = {
            if (showBottomAd) {
                GoogleNativeAd(
                    modifier = Modifier,
                    style = GoogleNativeAdStyle.Small,
                    nativeAd = nativeAd
                )
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            CategoryList(modifier = Modifier, list = categories) { id, title ->
                onCategoryClick(id, title)
            }
        }
    }
}