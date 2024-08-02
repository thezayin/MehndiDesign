package com.thezayin.preview.domain.repository

import com.thezayin.entities.PreviewMenu
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface PreviewMenuRepository {
fun getPreviewMenu(): Flow<Response<List<PreviewMenu>>>
}