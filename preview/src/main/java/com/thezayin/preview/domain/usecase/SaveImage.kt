package com.thezayin.preview.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.preview.domain.repository.SaveImageRepository
import kotlinx.coroutines.flow.Flow

interface SaveImage : suspend (String) -> Flow<Response<String>>

class SaveImageImpl(private val repository: SaveImageRepository) : SaveImage {
    override suspend fun invoke(url: String): Flow<Response<String>> =
        repository.saveImage(url)
}