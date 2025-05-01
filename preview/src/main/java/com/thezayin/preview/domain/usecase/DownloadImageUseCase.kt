package com.thezayin.preview.domain.usecase

import android.net.Uri
import com.thezayin.framework.model.Image
import com.thezayin.preview.domain.repository.ImageFileRepository

interface DownloadImageUseCase {
    suspend operator fun invoke(image: Image): Uri
}

class DownloadImageUseCaseImpl(
    private val repo: ImageFileRepository
): DownloadImageUseCase {
    override suspend fun invoke(image: Image): Uri =
        repo.saveImage(image)
}