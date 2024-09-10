package com.thezayin.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thezayin.data.paging.HomeImagePagingSource
import com.thezayin.databases.models.ImageModel
import com.thezayin.framework.utils.Response
import com.thezayin.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementation of the ImageRepository interface.
 *
 * This repository provides a flow of paginated image data using Paging 3 library.
 */
class ImageRepositoryImpl(
    private val imageDao: com.thezayin.databases.dao.HomeImageDao
) : ImageRepository {

    /**
     * Retrieves a paginated flow of image data.
     *
     * @return A Flow emitting a Response object containing another Flow of PagingData for ImageModel.
     */
    override fun getAllImages(): Flow<Response<Flow<PagingData<ImageModel>>>> = flow {
        try {
            // Emit loading state while setting up the pager.
            emit(Response.Loading)

            // Create a Pager instance with a PagingConfig and a paging source.
            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = { HomeImagePagingSource(imageDao) }
            ).flow

            // Emit the success state with the pager flow.
            emit(Response.Success(pager))
        } catch (exception: Exception) {
            // Emit the error state if an exception occurs.
            emit(Response.Error(exception.localizedMessage ?: "An error occurred"))
        }
    }
}