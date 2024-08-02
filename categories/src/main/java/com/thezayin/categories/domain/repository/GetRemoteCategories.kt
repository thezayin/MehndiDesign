package com.thezayin.categories.domain.repository

import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetRemoteCategories {
    fun getArabicImages(): Flow<Response<Boolean>>
    fun getBridalImages(): Flow<Response<Boolean>>
    fun getClassicImages(): Flow<Response<Boolean>>
    fun getFingerImages(): Flow<Response<Boolean>>
    fun getFootImages(): Flow<Response<Boolean>>
    fun getIndianImages(): Flow<Response<Boolean>>
    fun getIndoImages(): Flow<Response<Boolean>>
    fun getMoroccanImages(): Flow<Response<Boolean>>
    fun getPakistaniImages(): Flow<Response<Boolean>>
    fun getTattooImages(): Flow<Response<Boolean>>
    fun getTikkiImages(): Flow<Response<Boolean>>
}