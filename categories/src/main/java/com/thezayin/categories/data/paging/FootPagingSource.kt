package com.thezayin.categories.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thezayin.categories.data.local.dao.FootDao
import com.thezayin.entities.FootModel
import kotlinx.coroutines.delay

class FootPagingSource(
    private val dao: FootDao,
) : PagingSource<Int, FootModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FootModel> {
        val page = params.key ?: 0
        return try {
            val entities = dao.getImages(params.loadSize, page * params.loadSize)
            if (page != 0) delay(1000)
            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FootModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}