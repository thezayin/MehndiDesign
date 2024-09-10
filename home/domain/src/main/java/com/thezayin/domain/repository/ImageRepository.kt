package com.thezayin.domain.repository

import androidx.paging.PagingData
import com.thezayin.databases.models.ImageModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

/**
 * Interface for interacting with image data in the repository.
 */
interface ImageRepository {

    /**
     * Retrieves a paginated flow of image data.
     *
     * @return A [Flow] emitting [Response] objects. Each [Response] contains a [Flow] of [PagingData] for [ImageModel] objects.
     *         The [Response] can be in one of the following states:
     *         - [Response.Loading]: Indicates that the data is currently being loaded.
     *         - [Response.Success]: Contains a [Flow] of [PagingData] with [ImageModel] objects if the operation succeeds.
     *         - [Response.Error]: Contains an error message if the operation fails.
     */
    fun getAllImages(): Flow<Response<Flow<PagingData<ImageModel>>>>
}