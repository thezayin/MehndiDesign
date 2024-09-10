package com.thezayin.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow

/**
 * Interface for fetching remote image data.
 *
 * This use case is responsible for invoking the operation to fetch images from a remote repository.
 */
interface FetchRemote : suspend () -> Flow<Response<Boolean>>

/**
 * Implementation of [FetchRemote] that interacts with the [RemoteRepository] to fetch images.
 *
 * @property repository The remote repository used to fetch images.
 */
class FetchRemoteImpl(
    private val repository: RemoteRepository
) : FetchRemote {

    /**
     * Fetches images from the remote repository.
     *
     * @return A [Flow] emitting [Response] objects, where:
     *         - [Response.Loading] indicates that the fetch operation is in progress.
     *         - [Response.Success] contains a [Boolean] indicating whether the fetch operation was successful.
     *         - [Response.Error] contains an error message if the fetch operation fails.
     */
    override suspend fun invoke(): Flow<Response<Boolean>> = repository.getAllImages()
}