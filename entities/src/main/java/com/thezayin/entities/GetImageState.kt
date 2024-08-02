package com.thezayin.entities

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class GetImageState(
    val list: Flow<PagingData<ImageModel>> = emptyFlow(),
)