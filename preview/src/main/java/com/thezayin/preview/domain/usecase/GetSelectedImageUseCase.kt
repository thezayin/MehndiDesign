package com.thezayin.preview.domain.usecase


import com.thezayin.framework.model.Image
import com.thezayin.preview.domain.repository.ImageFileRepository

/**
 * Use-case: fetch the currently selected image.
 */
interface GetSelectedImageUseCase {
    operator fun invoke(): Image?
}

class GetSelectedImageUseCaseImpl(
    private val repository: ImageFileRepository
) : GetSelectedImageUseCase {
    override operator fun invoke(): Image? = repository.getSelectedImage()
}
