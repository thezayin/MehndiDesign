package com.thezayin.domain.repository

import androidx.paging.PagingData
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
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetCategoryImages {
    fun getArabicImages(): Flow<Response<Flow<PagingData<ArabicModel>>>>
    fun getBridalImages(): Flow<Response<Flow<PagingData<BridalModel>>>>
    fun getClassicImages(): Flow<Response<Flow<PagingData<ClassicModel>>>>
    fun getFingerImages(): Flow<Response<Flow<PagingData<FingerModel>>>>
    fun getFootImages(): Flow<Response<Flow<PagingData<FootModel>>>>
    fun getIndianImages(): Flow<Response<Flow<PagingData<IndianModel>>>>
    fun getIndoImages(): Flow<Response<Flow<PagingData<IndoModel>>>>
    fun getMoroccanImages(): Flow<Response<Flow<PagingData<MoroccanModel>>>>
    fun getPakistaniImages(): Flow<Response<Flow<PagingData<PakistaniModel>>>>
    fun getTattooImages(): Flow<Response<Flow<PagingData<TattooModel>>>>
    fun getTikkiImages(): Flow<Response<Flow<PagingData<TikkiModel>>>>
}