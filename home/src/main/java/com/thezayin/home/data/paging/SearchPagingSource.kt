package com.thezayin.home.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thezayin.entities.HomeImages
import com.thezayin.home.data.remote.SupabaseApiClient
import io.github.jan.supabase.postgrest.from

class SearchPagingSource(
    private val client: SupabaseApiClient,
) : PagingSource<Int, HomeImages>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeImages> {
        val currentPage = params.key ?: 1
        return try {
            val response = client.sup().from("mehndi_images").select().decodeList<HomeImages>()
            val endOfPaginationReached = response.lastIndex < currentPage
            if (response.isNotEmpty()) {
                LoadResult.Page(
                    data = response,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HomeImages>): Int? {
        return state.anchorPosition
    }

}