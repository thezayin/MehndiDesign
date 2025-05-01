package com.thezayin.favorites.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.thezayin.framework.R
import com.thezayin.framework.components.BannerAd
import com.thezayin.framework.components.LoadingImages
import com.thezayin.framework.components.TopBar
import com.thezayin.framework.model.Image

@Composable
fun FavoritesScreenContent(
    isLoading: Boolean,
    showBannerAd: Boolean,
    favorites: List<Image>,
    onBack: () -> Unit,
    onItemClick: (Image) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(R.color.background),
        topBar = {
            TopBar(onBackClick = onBack)
        },
        bottomBar = {
            BannerAd(showBannerAd)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (isLoading) {
                LoadingImages()
            }

            if (favorites.isEmpty()) {
                NoFavoriteItem()
            }
            FavoriteGrid(
                favorites = favorites,
                onItemClick = onItemClick,
            )
        }
    }
}