package com.thezayin.data.local.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thezayin.entities.ImageModel
import com.thezayin.framework.utils.Response
import com.thezayin.data.local.dao.HomeImageDao
import com.thezayin.data.paging.HomePagingSource
import com.thezayin.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImageRepositoryImpl(
    private val imageDao: HomeImageDao,
) :
    ImageRepository {
    override fun getAllImages(): Flow<Response<Flow<PagingData<ImageModel>>>> = flow {
        try {
            emit(Response.Loading)
            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = { HomePagingSource(imageDao) }
            ).flow
            emit(Response.Success(pager))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}