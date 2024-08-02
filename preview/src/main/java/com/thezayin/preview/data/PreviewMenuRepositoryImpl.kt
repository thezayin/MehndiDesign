package com.thezayin.preview.data

import com.thezayin.core.R
import com.thezayin.entities.PreviewMenu
import com.thezayin.framework.utils.Response
import com.thezayin.preview.domain.repository.PreviewMenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PreviewMenuRepositoryImpl : PreviewMenuRepository {
    override fun getPreviewMenu(): Flow<Response<List<PreviewMenu>>> = flow {
        try {
            val list = listOf(
                PreviewMenu(1, "Download", R.drawable.ic_download),
                PreviewMenu(2, "Share", R.drawable.ic_share_filled),
                PreviewMenu(3, "Rate", R.drawable.ic_like),
            )
            emit(Response.Success(list))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Error Occurred!"))
        }
    }
}