package com.thezayin.domain.usecase

import androidx.paging.PagingData
import com.thezayin.entities.ImageModel
import com.thezayin.framework.utils.Response
import com.thezayin.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow

interface GetHomeImages : () -> Flow<Response<Flow<PagingData<ImageModel>>>>
class GetHomeImagesImpl(
    private val repository: ImageRepository
) : GetHomeImages {
    override fun invoke() = repository.getAllImages()
}