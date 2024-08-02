package com.thezayin.like.data.repository

import com.thezayin.core.R
import com.thezayin.entities.PreviewMenu
import com.thezayin.framework.utils.Response
import com.thezayin.like.domain.repository.LikeMenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LikeMenuRepositoryImpl : LikeMenuRepository {
    override fun getMenuItems(): Flow<Response<List<PreviewMenu>>> = flow{
        try{
            emit(Response.Loading)
            val list = listOf(
                PreviewMenu(1, "Download", R.drawable.ic_download),
                PreviewMenu(2, "Remove", R.drawable.ic_unlike),
            )
            emit(Response.Success(list))
        }catch (e: Exception){
            emit(Response.Error(e.localizedMessage?: "An error occurred"))
        }
    }
}