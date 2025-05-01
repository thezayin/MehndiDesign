package com.thezayin.category.presentation.event

import com.thezayin.framework.model.Image

sealed class CategoryEvent {
    object Load : CategoryEvent()
    data class ClickImage(val image: Image) : CategoryEvent()
    data class ShowBannerAd(val show: Boolean) : CategoryEvent()
    data class ShowImageSelectionAd(val show: Boolean) : CategoryEvent()
}
