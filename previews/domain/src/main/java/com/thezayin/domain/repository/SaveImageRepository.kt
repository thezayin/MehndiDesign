package com.thezayin.domain.repository

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface SaveImageRepository {
    fun saveImage(url: String): Flow<Response<String>>
}