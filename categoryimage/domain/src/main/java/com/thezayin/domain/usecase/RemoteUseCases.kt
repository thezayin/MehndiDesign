package com.thezayin.domain.usecase

import com.thezayin.domain.repository.GetRemoteCategories
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface ArabicRemote : suspend () -> Flow<Response<Boolean>>
interface BridalRemote : suspend () -> Flow<Response<Boolean>>
interface ClassicRemote : suspend () -> Flow<Response<Boolean>>
interface FingerRemote : suspend () -> Flow<Response<Boolean>>
interface FootRemote : suspend () -> Flow<Response<Boolean>>
interface IndianRemote : suspend () -> Flow<Response<Boolean>>
interface IndoRemote : suspend () -> Flow<Response<Boolean>>
interface MoroccanRemote : suspend () -> Flow<Response<Boolean>>
interface PakistaniRemote : suspend () -> Flow<Response<Boolean>>
interface TattooRemote : suspend () -> Flow<Response<Boolean>>
interface TikkiRemote : suspend () -> Flow<Response<Boolean>>

class TikkiRemoteImpl(
    val repository: GetRemoteCategories
) : TikkiRemote {
    override suspend fun invoke(): Flow<Response<Boolean>> = repository.getTikkiImages()
}

class TattooRemoteImpl(
    val repository: GetRemoteCategories
) : TattooRemote {
    override suspend fun invoke(): Flow<Response<Boolean>> = repository.getTattooImages()
}

class PakistaniRemoteImpl(
    val repository: GetRemoteCategories
) : PakistaniRemote {
    override suspend fun invoke(): Flow<Response<Boolean>> = repository.getPakistaniImages()
}

class MoroccanRemoteImpl(
    val repository: GetRemoteCategories
) : MoroccanRemote {
    override suspend fun invoke(): Flow<Response<Boolean>> = repository.getMoroccanImages()
}

class IndoRemoteImpl(
    val repository: GetRemoteCategories
) : IndoRemote {
    override suspend fun invoke(): Flow<Response<Boolean>> = repository.getIndoImages()
}

class IndianRemoteImpl(
    val repository: GetRemoteCategories
) : IndianRemote {
    override suspend fun invoke(): Flow<Response<Boolean>> = repository.getIndianImages()
}

class FootRemoteImpl(
    val repository: GetRemoteCategories
) : FootRemote {
    override suspend fun invoke(): Flow<Response<Boolean>> = repository.getFootImages()
}

class FingerRemoteImpl(
    val repository: GetRemoteCategories
) : FingerRemote {
    override suspend fun invoke(): Flow<Response<Boolean>> = repository.getFingerImages()
}

class ClassicRemoteImpl(
    val repository: GetRemoteCategories
) : ClassicRemote {
    override suspend fun invoke(): Flow<Response<Boolean>> = repository.getClassicImages()
}

class BridalRemoteImpl(
    val repository: GetRemoteCategories
) : BridalRemote {
    override suspend fun invoke(): Flow<Response<Boolean>> = repository.getBridalImages()
}

class ArabicRemoteImpl(
    val repository: GetRemoteCategories
) : ArabicRemote {
    override suspend fun invoke(): Flow<Response<Boolean>> = repository.getArabicImages()
}