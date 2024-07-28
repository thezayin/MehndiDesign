package com.thezayin.home.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.thezayin.entities.HomeImages
import com.thezayin.entities.HomeRemoteKeys
import com.thezayin.home.data.local.database.HomeDatabase
import com.thezayin.home.data.remote.SupabaseApiClient
import io.github.jan.supabase.postgrest.from

@OptIn(ExperimentalPagingApi::class)
class ImageRemoteMediator(
    private val homeDatabase: HomeDatabase,
    private val supabaseApiClient: SupabaseApiClient,
) : RemoteMediator<Int, HomeImages>() {

    private val imageDao = homeDatabase.homeImageDao()
    private val remoteKeysDao = homeDatabase.homeRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, HomeImages>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response =
                supabaseApiClient.sup().from("mehndi_images").select().decodeList<HomeImages>()

            val endOfPaginationReached = response.isEmpty()
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            homeDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    imageDao.deleteAllImages()
                    remoteKeysDao.deleteAllRemoteKeys()
                    imageDao.deleteAllImages()
                }
                val keys = response.map { image ->
                    HomeRemoteKeys(
                        _id = image.image_id,
                        id = image.id.toString(),
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                remoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                imageDao.insertImages(images = response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }



    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, HomeImages>
    ): HomeRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getRemoteKeys(id = id.toString())
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, HomeImages>
    ): HomeRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { image ->
                remoteKeysDao.getRemoteKeys(id = image.id.toString())
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, HomeImages>
    ): HomeRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { image ->
                remoteKeysDao.getRemoteKeys(id = image.id.toString())
            }
    }
}