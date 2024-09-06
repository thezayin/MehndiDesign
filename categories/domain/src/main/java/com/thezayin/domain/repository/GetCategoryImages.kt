package com.thezayin.domain.repository

import androidx.paging.PagingData
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