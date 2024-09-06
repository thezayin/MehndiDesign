package com.thezayin.data.local.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thezayin.data.local.dao.ArabicDao
import com.thezayin.data.local.dao.BridalDao
import com.thezayin.data.local.dao.ClassicDao
import com.thezayin.data.local.dao.FingerDao
import com.thezayin.data.local.dao.FootDao
import com.thezayin.data.local.dao.IndianDao
import com.thezayin.data.local.dao.IndoDao
import com.thezayin.data.local.dao.MoroccanDao
import com.thezayin.data.local.dao.PakistaniDao
import com.thezayin.data.local.dao.TattooDao
import com.thezayin.data.local.dao.TikkiDao
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
import com.thezayin.domain.repository.GetCategoryImages
import com.thezayin.entities.ArabicModel
import com.thezayin.entities.BridalModel
import com.thezayin.entities.ClassicModel
import com.thezayin.entities.FingerModel
import com.thezayin.entities.FootModel
import com.thezayin.entities.IndianModel
import com.thezayin.entities.IndoModel
import com.thezayin.entities.MoroccanModel
import com.thezayin.entities.PakistaniModel
import com.thezayin.entities.TattooModel
import com.thezayin.entities.TikkiModel
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCategoryImagesImpl(
    private val arabicDao: ArabicDao,
    private val bridalDao: BridalDao,
    private val classicDao: ClassicDao,
    private val fingerDao: FingerDao,
    private val footDao: FootDao,
    private val indianDao: IndianDao,
    private val indoDao: IndoDao,
    private val pakistaniDao: PakistaniDao,
    private val moroccanDao: MoroccanDao,
    private val tattooDao: TattooDao,
    private val tikkiDao: TikkiDao
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