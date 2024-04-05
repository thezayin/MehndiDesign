package com.thezayin.mehndidesign.domain.usecase

import androidx.paging.PagingData
import com.thezayin.mehndidesign.domain.model.UnsplashImage
import com.thezayin.mehndidesign.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

interface GetAllImagesUseCase : () -> Flow<PagingData<UnsplashImage>>
class GetAllImagesUseCaseImpl(
    private val repository: Repository
) : GetAllImagesUseCase {
    override fun invoke() = repository.getAllImages()
}