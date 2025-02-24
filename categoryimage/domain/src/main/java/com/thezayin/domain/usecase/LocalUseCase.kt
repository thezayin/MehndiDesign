package com.thezayin.domain.usecase

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
import com.thezayin.domain.repository.GetCategoryImages
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface ArabicImages : suspend () -> Flow<Response<Flow<PagingData<ArabicModel>>>>
interface BridalImages : suspend () -> Flow<Response<Flow<PagingData<BridalModel>>>>
interface ClassicImages : suspend () -> Flow<Response<Flow<PagingData<ClassicModel>>>>
interface FingerImages : suspend () -> Flow<Response<Flow<PagingData<FingerModel>>>>
interface FootImages : suspend () -> Flow<Response<Flow<PagingData<FootModel>>>>
interface IndianImages : suspend () -> Flow<Response<Flow<PagingData<IndianModel>>>>
interface IndoImages : suspend () -> Flow<Response<Flow<PagingData<IndoModel>>>>
interface MoroccanImages : suspend () -> Flow<Response<Flow<PagingData<MoroccanModel>>>>
interface PakistaniImages : suspend () -> Flow<Response<Flow<PagingData<PakistaniModel>>>>
interface TattooImages : suspend () -> Flow<Response<Flow<PagingData<TattooModel>>>>
interface TikkiImages : suspend () -> Flow<Response<Flow<PagingData<TikkiModel>>>>

class ArabicImagesImpl(
    private val repository: GetCategoryImages
) : ArabicImages {
    override suspend fun invoke(): Flow<Response<Flow<PagingData<ArabicModel>>>> =
        repository.getArabicImages()
}

class BridalImagesImpl(
    private val repository: GetCategoryImages
) : BridalImages {
    override suspend fun invoke(): Flow<Response<Flow<PagingData<BridalModel>>>> =
        repository.getBridalImages()
}

class ClassicImagesImpl(
    private val repository: GetCategoryImages
) : ClassicImages {
    override suspend fun invoke(): Flow<Response<Flow<PagingData<ClassicModel>>>> =
        repository.getClassicImages()
}

class FingerImagesImpl(
    private val repository: GetCategoryImages
) : FingerImages {
    override suspend fun invoke(): Flow<Response<Flow<PagingData<FingerModel>>>> =
        repository.getFingerImages()
}

class FootImagesImpl(
    private val repository: GetCategoryImages
) : FootImages {
    override suspend fun invoke(): Flow<Response<Flow<PagingData<FootModel>>>> =
        repository.getFootImages()
}

class IndianImagesImpl(
    private val repository: GetCategoryImages
) : IndianImages {
    override suspend fun invoke(): Flow<Response<Flow<PagingData<IndianModel>>>> =
        repository.getIndianImages()
}

class IndoImagesImpl(
    private val repository: GetCategoryImages
) : IndoImages {
    override suspend fun invoke(): Flow<Response<Flow<PagingData<IndoModel>>>> =
        repository.getIndoImages()
}

class MoroccanImagesImpl(
    private val repository: GetCategoryImages
) : MoroccanImages {
    override suspend fun invoke(): Flow<Response<Flow<PagingData<MoroccanModel>>>> =
        repository.getMoroccanImages()
}

class PakistaniImagesImpl(
    private val repository: GetCategoryImages
) : PakistaniImages {
    override suspend fun invoke(): Flow<Response<Flow<PagingData<PakistaniModel>>>> =
        repository.getPakistaniImages()
}

class TattooImagesImpl(
    private val repository: GetCategoryImages
) : TattooImages {
    override suspend fun invoke(): Flow<Response<Flow<PagingData<TattooModel>>>> =
        repository.getTattooImages()
}

class TikkiImagesImpl(
    private val repository: GetCategoryImages
) : TikkiImages {
    override suspend fun invoke(): Flow<Response<Flow<PagingData<TikkiModel>>>> =
        repository.getTikkiImages()
}