package com.thezayin.like.domain.usecase

import com.thezayin.entities.PreviewMenu
import com.thezayin.framework.utils.Response
import com.thezayin.like.domain.repository.LikeMenuRepository
import kotlinx.coroutines.flow.Flow

interface LikeMenuList : suspend () -> Flow<Response<List<PreviewMenu>>>
class LikeMenuListImpl(private val repository: LikeMenuRepository) : LikeMenuList {
    override suspend fun invoke():  Flow<Response<List<PreviewMenu>>> {
        return repository.getMenuItems()
    }
}