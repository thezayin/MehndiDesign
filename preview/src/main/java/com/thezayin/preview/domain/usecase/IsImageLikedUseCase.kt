package com.thezayin.preview.domain.usecase

import com.thezayin.framework.model.Image
import com.thezayin.preview.domain.repository.ImageFileRepository

/** Returns true if this image is already saved in liked_images. */
interface IsImageLikedUseCase {
    operator fun invoke(image: Image): Boolean
}

class IsImageLikedUseCaseImpl(
    private val repo: ImageFileRepository
) : IsImageLikedUseCase {
    override operator fun invoke(image: Image): Boolean =
        repo.isLiked(image)
}
