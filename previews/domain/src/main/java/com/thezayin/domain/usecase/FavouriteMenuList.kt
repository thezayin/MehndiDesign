package com.thezayin.domain.usecase

import com.thezayin.entities.PreviewMenu
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface FavouriteMenuList : suspend () -> Flow<Response<List<PreviewMenu>>>
class FavouriteMenuListImpl(private val repository: com.thezayin.domain.repository.FavouriteMenuRepository) :
    FavouriteMenuList {
    override suspend fun invoke(): Flow<Response<List<PreviewMenu>>> {
        return repository.getMenuItems()
    }
}