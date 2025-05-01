package com.thezayin.preview.domain.usecase

import android.net.Uri
import com.thezayin.framework.model.Image
import com.thezayin.preview.domain.repository.ImageFileRepository

interface GetDownloadedImageUriUseCase {
    operator fun invoke(image: Image): Uri?
}

class GetDownloadedImageUriUseCaseImpl(
    private val repo: ImageFileRepository
) : GetDownloadedImageUriUseCase {
    override operator fun invoke(image: Image): Uri? =
        repo.getImageUri(image)
}