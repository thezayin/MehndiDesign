package com.thezayin.domain.repository

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    fun getAllImages(): Flow<Response<Boolean>>
}