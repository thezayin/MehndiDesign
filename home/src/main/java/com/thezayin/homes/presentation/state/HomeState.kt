package com.thezayin.homes.presentation.state

import androidx.paging.PagingData
import com.thezayin.framework.model.Category
import com.thezayin.framework.model.Image
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val isLoading: Boolean = false,
    val homeImages: Flow<PagingData<Image>>? = null,
    val homeCategories: List<Category>? = null,
    val errorDialog: Boolean = false,
    val errorMessage: String = "",
    val showBannerAd: Boolean = true,
    val showImageSelectionAd: Boolean = true,
    val showCategorySelectionAd: Boolean = true,
    val showMyLikeClickAd: Boolean = true,
)