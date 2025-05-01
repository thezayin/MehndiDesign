package com.thezayin.homes.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.paging.compose.LazyPagingItems
import com.thezayin.framework.components.BannerAd
import com.thezayin.framework.model.Category
import com.thezayin.framework.model.Image
import ir.kaaveh.sdpcompose.sdp

@Composable
fun HomeScreenContent(
    categories: List<Category>?,
    homeImages: LazyPagingItems<Image>?,
    onCategoryClick: (Category) -> Unit,
    showBannerAd: Boolean,
    onImageClick: (Image) -> Unit,
    onSettingClick: () -> Unit = {},
    onLikeClick: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        topBar = {
            HomeTopBar(
                settingCallback = onSettingClick,
                likeCallback =onLikeClick ,
                onPremiumClick = {}
            )
        },
        containerColor = colorResource(com.thezayin.framework.R.color.background),
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
                    .padding(top = 10.sdp)
            )
            {
                CategoryCarousel(
                    modifier = Modifier.padding(horizontal = 5.sdp),
                    categories = categories,
                    onCategoryClick = onCategoryClick
                )
                HomeImages(
                    modifier = Modifier,
                    items = homeImages,
                    onClick = onImageClick
                )
            }
        },
        bottomBar = {
            BannerAd(showBannerAd)
        }
    )
}
