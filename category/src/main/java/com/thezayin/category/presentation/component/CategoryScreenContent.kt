package com.thezayin.category.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.thezayin.category.presentation.state.CategoryState
import com.thezayin.framework.R
import com.thezayin.framework.components.BannerAd
import com.thezayin.framework.components.LoadingImages
import com.thezayin.framework.components.TopBar
import com.thezayin.framework.model.Image

@Composable
fun CategoryScreenContent(
    state: CategoryState,
    showBannerAd: Boolean,
    onBack: () -> Unit,
    onImageClick: (Image) -> Unit
) {
    Scaffold(
        containerColor = colorResource(R.color.background),
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        topBar = {
            TopBar(onBackClick = onBack)
        },
        bottomBar = {
            BannerAd(showBannerAd)
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                state.isLoading -> {
                    LoadingImages()
                }

                state.error != null -> {
                    CategoryError(state.error)
                }

                else -> {
                    if (state.images.isEmpty()) EmptyCategory()
                    CategoryImagesGrid(
                        images = state.images,
                        onClick = onImageClick
                    )
                }
            }
        }
    }
}
