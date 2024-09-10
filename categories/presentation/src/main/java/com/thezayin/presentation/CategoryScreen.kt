package com.thezayin.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.thezayin.presentation.component.CategoryScreenContent
import org.koin.compose.koinInject

@Composable
fun CategoryScreen(
    onBackClick: () -> Unit,
    onCategoryClick: (Int, String?) -> Unit
) {
    val viewModel: CategoryViewModel = koinInject()

    val scope = rememberCoroutineScope()
    val nativeAd = remember { viewModel.nativeAd }
    val checkNetwork = remember { mutableStateOf(false) }
    val state = viewModel.categoryListState.collectAsState().value

    CategoryScreenContent(
        isError = state.showError,
        isLoading = state.isLoading,
        nativeAd = nativeAd.value,
        scope = scope,
        categories = state.categories,
        checkNetwork = checkNetwork,
        getNativeAd = { viewModel.getNativeAd() },
        onBackClick = onBackClick,
        hideErrorDialog = { viewModel.hideErrorDialog() },
        onCategoryClick = onCategoryClick
    )
}