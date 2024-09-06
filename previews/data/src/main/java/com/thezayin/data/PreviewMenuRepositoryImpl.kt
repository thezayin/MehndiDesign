package com.thezayin.data

import com.thezayin.drawable.R
import com.thezayin.domain.repository.PreviewMenuRepository
import com.thezayin.entities.PreviewMenu
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PreviewMenuRepositoryImpl : PreviewMenuRepository {
    override fun getPreviewMenu(): Flow<Response<List<PreviewMenu>>> = flow {
        try {
            val list = listOf(
                PreviewMenu(1, "Download", R.drawable.ic_download),
                PreviewMenu(2, "Like", R.drawable.ic_like),
            )
            emit(Response.Success(list))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Error Occurred!"))
        }
    }
}