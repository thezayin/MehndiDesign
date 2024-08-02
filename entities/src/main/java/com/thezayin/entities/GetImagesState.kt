package com.thezayin.entities

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class GetImagesState(
    val list: Flow<PagingData<String>> = emptyFlow()
)