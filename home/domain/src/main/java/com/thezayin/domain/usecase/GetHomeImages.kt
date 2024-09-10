package com.thezayin.domain.usecase

import androidx.paging.PagingData
import com.thezayin.databases.models.ImageModel
import com.thezayin.framework.utils.Response
import com.thezayin.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow

/**
 * Interface for fetching home images from the repository.
 *
 * This use case provides access to a flow of paginated image data, encapsulated in a [Response] object.
 */
interface GetHomeImages : () -> Flow<Response<Flow<PagingData<ImageModel>>>>

/**
 * Implementation of [GetHomeImages] that interacts with the [ImageRepository] to fetch images.
 *
 * @property repository The image repository used to fetch home images.
 */
class GetHomeImagesImpl(
    private val repository: ImageRepository
) : GetHomeImages {

    /**
     * Retrieves home images from the image repository.
     *
     * @return A [Flow] emitting [Response] objects, where:
     *         - [Response.Loading] indicates that the image fetching operation is in progress.
     *         - [Response.Success] contains a [Flow] of [PagingData] with [ImageModel] items.
     *         - [Response.Error] contains an error message if the fetch operation fails.
     */
    override fun invoke(): Flow<Response<Flow<PagingData<ImageModel>>>> = repository.getAllImages()
}