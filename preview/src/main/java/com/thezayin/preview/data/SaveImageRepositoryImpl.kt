package com.thezayin.preview.data

import com.thezayin.framework.extension.functions.saveToInternalStorage
import com.thezayin.framework.utils.Response
import com.thezayin.preview.domain.repository.SaveImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveImageRepositoryImpl : SaveImageRepository {
    override fun saveImage(url: String): Flow<Response<String>> = flow {
        try {
            emit(Response.Loading)
            val uri = saveToInternalStorage(url)
            emit(Response.Success(uri))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Error Occurred"))
        }
    }
}