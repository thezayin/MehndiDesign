package com.thezayin.preview.domain.usecase

import com.thezayin.entities.PreviewMenu
import com.thezayin.framework.utils.Response
import com.thezayin.preview.domain.repository.PreviewMenuRepository
import kotlinx.coroutines.flow.Flow

interface PreviewMenuItems : suspend () -> Flow<Response<List<PreviewMenu>>>

class PreviewMenuItemsImpl(private val repository: PreviewMenuRepository) : PreviewMenuItems {
    override suspend fun invoke(): Flow<Response<List<PreviewMenu>>> = repository.getPreviewMenu()
}