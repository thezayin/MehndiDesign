package com.thezayin.like.domain.repository

import com.thezayin.entities.PreviewMenu
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface LikeMenuRepository {
    fun getMenuItems():Flow<Response<List<PreviewMenu>>>
}