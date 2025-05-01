package com.thezayin.preview.domain.usecase

import com.thezayin.framework.model.Image
import com.thezayin.preview.domain.repository.ImageFileRepository

interface ToggleLikeUseCase {
    suspend operator fun invoke(image: Image): Boolean
}

class ToggleLikeUseCaseImpl(
    private val repo: ImageFileRepository
) : ToggleLikeUseCase {
    override suspend fun invoke(image: Image): Boolean =
        repo.toggleLike(image)
}