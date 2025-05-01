package com.thezayin.upload.data.repository

import android.net.Uri
import com.thezayin.upload.data.remote.AdminRemoteDataSource
import com.thezayin.upload.domain.model.ImageModel
import com.thezayin.upload.domain.repository.AdminRepository


class AdminRepositoryImpl(
    private val remoteDataSource: AdminRemoteDataSource
) : AdminRepository {
    override suspend fun uploadImages(uris: List<Uri>, tableName: String): Result<List<ImageModel>> {
        return remoteDataSource.uploadImages(uris, tableName)
    }
}