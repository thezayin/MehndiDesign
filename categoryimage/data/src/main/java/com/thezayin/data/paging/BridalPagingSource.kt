package com.thezayin.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thezayin.databases.models.BridalModel
import kotlinx.coroutines.delay

class BridalPagingSource(
    private val dao: com.thezayin.databases.dao.BridalDao,
) : PagingSource<Int, BridalModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BridalModel> {
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

    override fun getRefreshKey(state: PagingState<Int, BridalModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}