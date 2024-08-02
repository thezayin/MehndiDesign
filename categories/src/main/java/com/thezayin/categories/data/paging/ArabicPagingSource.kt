package com.thezayin.categories.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thezayin.categories.data.local.dao.ArabicDao
import com.thezayin.entities.ArabicModel
import kotlinx.coroutines.delay

class ArabicPagingSource(
    private val dao: ArabicDao,
) : PagingSource<Int, ArabicModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArabicModel> {
        val page = params.key ?: 0
        return try {
            val entities = dao.getImages(params.loadSize, page * params.loadSize)
            Log.d("jejeArabic", "load: $entities")
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

    override fun getRefreshKey(state: PagingState<Int, ArabicModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}