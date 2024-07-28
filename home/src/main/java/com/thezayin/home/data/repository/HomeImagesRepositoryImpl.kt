package com.thezayin.home.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thezayin.entities.HomeImages
import com.thezayin.framework.utils.Response
import com.thezayin.home.data.local.database.HomeDatabase
import com.thezayin.home.data.paging.ImageRemoteMediator
import com.thezayin.home.data.remote.SupabaseApiClient
import com.thezayin.home.domain.repository.HomeImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeImagesRepositoryImpl(
    private val supabaseApiClient: SupabaseApiClient,
    private val database: HomeDatabase
) : HomeImagesRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getAllImages(): Flow<Response<Flow<PagingData<HomeImages>>>> = flow {
        try {
            emit(Response.Loading)
            val pagingSourceFactory = { database.homeImageDao().getAllImages() }
            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                remoteMediator = ImageRemoteMediator(
                    supabaseApiClient = supabaseApiClient,
                    homeDatabase = database
                ),
                pagingSourceFactory = pagingSourceFactory
            ).flow
            emit(Response.Success(pager))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }

    }
}