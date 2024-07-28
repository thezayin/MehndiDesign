package com.thezayin.home.domain.usecase

import androidx.paging.PagingData
import com.thezayin.entities.HomeImages
import com.thezayin.framework.utils.Response
import com.thezayin.home.domain.repository.HomeImagesRepository
import kotlinx.coroutines.flow.Flow

interface GetHomeImages : () -> Flow<Response<Flow<PagingData<HomeImages>>>>
class GetHomeImagesImpl(
    private val repository: HomeImagesRepository
) : GetHomeImages {
    override fun invoke() = repository.getAllImages()
}