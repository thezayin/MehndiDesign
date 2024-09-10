package com.thezayin.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.paging.compose.collectAsLazyPagingItems
import com.thezayin.presentation.component.CategoryImageContent
import org.koin.compose.koinInject

@Composable
fun CategoryImageScreen(
    id: Int,
    title: String,
    onBackClick: () -> Unit
) {
    val viewModel: CategoryImageViewModel = koinInject()
    val state = viewModel.categoryImages.collectAsState().value

    val scope = rememberCoroutineScope()
    val nativeAd = remember { viewModel.nativeAd }
    val checkNetwork = remember { mutableStateOf(false) }

    CategoryImageContent(
        id = id,
        scope = scope,
        title = title,
        nativeAd = nativeAd.value,
        isError = state.showError,
        isLoading = state.isLoading,
        errorMessage = state.errorMessage,
        items = state.images?.collectAsLazyPagingItems(),
        onBackClick = onBackClick,
        checkNetwork = checkNetwork,
        getNativeAd = { viewModel.getNativeAd() },
        hideErrorDialog = { viewModel.hideErrorDialog() },
        fetchRemoteFoot = { viewModel.fetchRemoteFoot() },
        fetchRemoteIndo = { viewModel.fetchRemoteIndo() },
        fetchRemoteTikki = { viewModel.fetchRemoteTikki() },
        fetchRemoteTattoo = { viewModel.fetchRemoteTattoo() },
        fetchRemoteBridal = { viewModel.fetchRemoteBridal() },
        fetchRemoteIndian = { viewModel.fetchRemoteIndian() },
        fetchRemoteArabic = { viewModel.fetchRemoteArabic() },
        fetchRemoteFinger = { viewModel.fetchRemoteFinger() },
        fetchRemoteClassic = { viewModel.fetchRemoteClassic() },
        fetchRemotePakistani = { viewModel.fetchRemotePakistani() },
        fetchRemoteMoroccan = { viewModel.fetchRemoteMoroccan() }
    )
}