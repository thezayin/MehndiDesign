package com.thezayin.domain.repository

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

/**
 * Interface for interacting with remote image data sources.
 */
interface RemoteRepository {

    /**
     * Fetches all images from the remote source and updates the local database.
     *
     * @return A [Flow] emitting [Response] objects. Each [Response] indicates the result of the fetch operation:
     *         - [Response.Loading]: Indicates that the fetch operation is in progress.
     *         - [Response.Success]: Contains a [Boolean] value indicating whether the operation was successful.
     *         - [Response.Error]: Contains an error message if the operation fails.
     */
    fun getAllImages(): Flow<Response<Boolean>>
}