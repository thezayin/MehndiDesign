package com.thezayin.upload.domain.usecase

import android.net.Uri
import com.thezayin.upload.domain.model.ImageModel
import com.thezayin.upload.domain.repository.AdminRepository

interface UploadImagesUseCase {
    suspend operator fun invoke(uris: List<Uri>, tableName: String): Result<List<ImageModel>>
}
class UploadImagesUseCaseImpl(
    private val adminRepository: AdminRepository
) : UploadImagesUseCase {
    override suspend fun invoke(uris: List<Uri>, tableName: String): Result<List<ImageModel>> {
        return adminRepository.uploadImages(uris, tableName)
    }
}