package com.thezayin.home.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.home.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow

interface FetchRemote : suspend () -> Flow<Response<Boolean>>

class FetchRemoteImpl(private val repository: RemoteRepository) : FetchRemote {
    override suspend fun invoke(): Flow<Response<Boolean>> = repository.getAllImages()
}