package com.thezayin.domain.usecase

import com.thezayin.entities.PreviewMenu
import com.thezayin.framework.utils.Response
import com.thezayin.domain.repository.PreviewMenuRepository
import kotlinx.coroutines.flow.Flow

interface PreviewItems : suspend () -> Flow<Response<List<PreviewMenu>>>

class PreviewItemsImpl(private val repository: PreviewMenuRepository) : PreviewItems {
    override suspend fun invoke(): Flow<Response<List<PreviewMenu>>> = repository.getPreviewMenu()
}