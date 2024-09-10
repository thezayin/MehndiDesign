package com.thezayin.data.local.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thezayin.data.paging.ArabicPagingSource
import com.thezayin.data.paging.BridalPagingSource
import com.thezayin.data.paging.ClassicPagingSource
import com.thezayin.data.paging.FingerPagingSource
import com.thezayin.data.paging.FootPagingSource
import com.thezayin.data.paging.IndianPagingSource
import com.thezayin.data.paging.IndoPagingSource
import com.thezayin.data.paging.MoroccanPagingSource
import com.thezayin.data.paging.PakistaniPagingSource
import com.thezayin.data.paging.TattooPagingSource
import com.thezayin.data.paging.TikkiPagingSource
import com.thezayin.databases.models.ArabicModel
import com.thezayin.databases.models.BridalModel
import com.thezayin.databases.models.ClassicModel
import com.thezayin.databases.models.FingerModel
import com.thezayin.databases.models.FootModel
import com.thezayin.databases.models.IndianModel
import com.thezayin.databases.models.IndoModel
import com.thezayin.databases.models.MoroccanModel
import com.thezayin.databases.models.PakistaniModel
import com.thezayin.databases.models.TattooModel
import com.thezayin.databases.models.TikkiModel
import com.thezayin.domain.repository.GetCategoryImages
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCategoryImagesImpl(
    private val arabicDao: com.thezayin.databases.dao.ArabicDao,
    private val bridalDao: com.thezayin.databases.dao.BridalDao,
    private val classicDao: com.thezayin.databases.dao.ClassicDao,
    private val fingerDao: com.thezayin.databases.dao.FingerDao,
    private val footDao: com.thezayin.databases.dao.FootDao,
    private val indianDao: com.thezayin.databases.dao.IndianDao,
    private val indoDao: com.thezayin.databases.dao.IndoDao,
    private val pakistaniDao: com.thezayin.databases.dao.PakistaniDao,
    private val moroccanDao: com.thezayin.databases.dao.MoroccanDao,
    private val tattooDao: com.thezayin.databases.dao.TattooDao,
    private val tikkiDao: com.thezayin.databases.dao.TikkiDao
) : GetCategoryImages {
    override fun getArabicImages(): Flow<Response<Flow<PagingData<ArabicModel>>>> = flow {
        try {
            emit(Response.Loading)
            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = { ArabicPagingSource(arabicDao) }
            ).flow
            emit(Response.Success(pager))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getBridalImages(): Flow<Response<Flow<PagingData<BridalModel>>>> = flow {
        try {
            emit(Response.Loading)
            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = { BridalPagingSource(bridalDao) }
            ).flow
            emit(Response.Success(pager))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getClassicImages(): Flow<Response<Flow<PagingData<ClassicModel>>>> = flow {
        try {
            emit(Response.Loading)
            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = { ClassicPagingSource(classicDao) }
            ).flow
            emit(Response.Success(pager))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getFingerImages(): Flow<Response<Flow<PagingData<FingerModel>>>> = flow {
        try {
            emit(Response.Loading)
            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = { FingerPagingSource(fingerDao) }
            ).flow
            emit(Response.Success(pager))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getFootImages(): Flow<Response<Flow<PagingData<FootModel>>>> = flow {
        try {
            emit(Response.Loading)
            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = { FootPagingSource(footDao) }
            ).flow
            emit(Response.Success(pager))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getIndianImages(): Flow<Response<Flow<PagingData<IndianModel>>>> = flow {
        try {
            emit(Response.Loading)
            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = { IndianPagingSource(indianDao) }
            ).flow
            emit(Response.Success(pager))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getIndoImages(): Flow<Response<Flow<PagingData<IndoModel>>>> = flow {
        try {
            emit(Response.Loading)
            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = { IndoPagingSource(indoDao) }
            ).flow
            emit(Response.Success(pager))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getMoroccanImages(): Flow<Response<Flow<PagingData<MoroccanModel>>>> = flow {
        try {
            emit(Response.Loading)
            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = { MoroccanPagingSource(moroccanDao) }
            ).flow
            emit(Response.Success(pager))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getPakistaniImages(): Flow<Response<Flow<PagingData<PakistaniModel>>>> = flow {
        try {
            emit(Response.Loading)
            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = { PakistaniPagingSource(pakistaniDao) }
            ).flow
            emit(Response.Success(pager))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getTattooImages(): Flow<Response<Flow<PagingData<TattooModel>>>> = flow {
        try {
            emit(Response.Loading)
            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = { TattooPagingSource(tattooDao) }
            ).flow
            emit(Response.Success(pager))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getTikkiImages(): Flow<Response<Flow<PagingData<TikkiModel>>>> = flow {
        try {
            emit(Response.Loading)
            val pager = Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = { TikkiPagingSource(tikkiDao) }
            ).flow
            emit(Response.Success(pager))

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}