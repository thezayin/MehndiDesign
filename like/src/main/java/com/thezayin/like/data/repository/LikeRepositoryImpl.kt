package com.thezayin.like.data.repository

import com.thezayin.entities.LikeImageModel
import com.thezayin.framework.utils.Response
import com.thezayin.like.data.dao.LikeDao
import com.thezayin.like.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LikeRepositoryImpl(private val dao: LikeDao) : LikeRepository {
    override fun getAllImages(): Flow<Response<List<LikeImageModel>>> = flow {
        try {
            emit(Response.Loading)
            val list = dao.getAllImages()
            emit(Response.Success(list))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun insertImages(url: String): Flow<Response<Long>> = flow {
        try {
            emit(Response.Loading)
            val row = dao.insertImages(
                LikeImageModel(
                    URL = url
                )
            )
            emit(Response.Success(row))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun deleteImage(id: Int): Flow<Response<Int>> = flow {
        try {
            emit(Response.Loading)
            val row = dao.deleteImage(id)
            emit(Response.Success(row))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}