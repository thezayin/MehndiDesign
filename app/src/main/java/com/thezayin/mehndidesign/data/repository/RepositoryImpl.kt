package com.thezayin.mehndidesign.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thezayin.mehndidesign.data.local.database.UnsplashDatabase
import com.thezayin.mehndidesign.data.paging.SearchPagingSource
import com.thezayin.mehndidesign.data.paging.UnsplashRemoteMediator
import com.thezayin.mehndidesign.data.remote.UnsplashApi
import com.thezayin.mehndidesign.domain.model.UnsplashImage
import com.thezayin.mehndidesign.domain.repository.Repository
import com.thezayin.mehndidesign.presentation.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) : Repository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getAllImages(): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                unsplashApi = unsplashApi,
                unsplashDatabase = unsplashDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchImages(query: String): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(unsplashApi = unsplashApi, query = query)
            }
        ).flow
    }
}