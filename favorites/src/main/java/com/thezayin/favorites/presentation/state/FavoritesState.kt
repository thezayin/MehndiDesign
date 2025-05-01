package com.thezayin.favorites.presentation.state

import com.thezayin.framework.model.Image

data class FavoritesState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val favorites: List<Image> = emptyList(),
    val error: String? = null,
    val showBannerAd: Boolean = false,
    val showImageSelectionAd: Boolean = false,
)
