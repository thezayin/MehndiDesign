package com.thezayin.favorites.presentation.event

import com.thezayin.framework.model.Image

sealed class FavoritesEvent {
    object Load : FavoritesEvent()
    data class Remove(val image: Image) : FavoritesEvent()
    data class Click(val image: Image) : FavoritesEvent()
    data class ShowImageSelectionAd(val show: Boolean) : FavoritesEvent()
    data class ShowBannerAd(val show: Boolean) : FavoritesEvent()
}
