package com.thezayin.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.paging.compose.LazyPagingItems
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.components.ErrorQueryDialog
import com.thezayin.components.LoadingDialog
import com.thezayin.components.NetworkDialog
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import com.thezayin.values.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@Composable
fun CategoryImageContent(
    id: Int,
    title: String,
    isError: Boolean,
    isLoading: Boolean,
    nativeAd: NativeAd?,
    errorMessage: String,
    scope: CoroutineScope,
    items: LazyPagingItems<String>?,
    checkNetwork: MutableState<Boolean>,
    getNativeAd: () -> Unit,
    onBackClick: () -> Unit,
    hideErrorDialog: () -> Unit,
    fetchRemoteFoot: () -> Unit,
    fetchRemoteIndo: () -> Unit,
    fetchRemoteTikki: () -> Unit,
    fetchRemoteTattoo: () -> Unit,
    fetchRemoteBridal: () -> Unit,
    fetchRemoteIndian: () -> Unit,
    fetchRemoteArabic: () -> Unit,
    fetchRemoteFinger: () -> Unit,
    fetchRemoteClassic: () -> Unit,
    fetchRemotePakistani: () -> Unit,
    fetchRemoteMoroccan: () -> Unit,
) {
    if (checkNetwork.value) {
        NetworkDialog(showDialog = { checkNetwork.value = it })
    }

    LaunchedEffect(key1 = Unit) {
        when (id) {
            1 -> fetchRemoteBridal()
            2 -> fetchRemoteIndian()
            3 -> fetchRemoteArabic()
            4 -> fetchRemotePakistani()
            5 -> fetchRemoteMoroccan()
            6 -> fetchRemoteClassic()
            7 -> fetchRemoteFinger()
            8 -> fetchRemoteFoot()
            9 -> fetchRemoteTikki()
            10 -> fetchRemoteIndo()
            11 -> fetchRemoteTattoo()
        }
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

            else -> {}
        }
    }

    if (isError) {
        ErrorQueryDialog(
            showDialog = { hideErrorDialog() },
            callback = {},
            error = "Unstable internet Connection"
        )
    }

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
            showAd = true
        )
    }


    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = {
            CategoryImageTopBar(
                modifier = Modifier,
                screenTitle = title,
                onBackClick = {
                    onBackClick()
                }
            )

        },
        bottomBar = {
            GoogleNativeAd(
                modifier = Modifier,
                style = GoogleNativeAdStyle.Small,
                nativeAd = nativeAd
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            CategoryImagesList(items = items, modifier = Modifier) {
            }
        }
    }
}