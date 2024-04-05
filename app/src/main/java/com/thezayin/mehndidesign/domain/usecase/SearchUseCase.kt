package com.thezayin.mehndidesign.domain.usecase

import androidx.paging.PagingData
import com.thezayin.mehndidesign.domain.model.UnsplashImage
import com.thezayin.mehndidesign.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

interface SearchUseCase : (String) -> Flow<PagingData<UnsplashImage>>
class SearchUseCaseImpl(
    private val repository: Repository
) : SearchUseCase {
    override fun invoke(query: String) = repository.searchImages(query)
}