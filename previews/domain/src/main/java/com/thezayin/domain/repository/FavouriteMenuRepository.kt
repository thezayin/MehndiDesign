package com.thezayin.domain.repository

import com.thezayin.entities.PreviewMenu
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface FavouriteMenuRepository {
    fun getMenuItems(): Flow<Response<List<PreviewMenu>>>
}