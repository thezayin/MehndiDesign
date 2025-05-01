package com.thezayin.category.presentation.state

import com.thezayin.framework.model.Image

data class CategoryState(
    val isLoading: Boolean = false,
    val images: List<Image> = emptyList(),
    val error: String? = null,
    val showBannerAd:Boolean = false,
    val showImageSelectionAd:Boolean = false,
)
