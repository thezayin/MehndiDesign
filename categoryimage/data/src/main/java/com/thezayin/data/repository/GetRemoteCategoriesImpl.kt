package com.thezayin.data.repository

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
import com.thezayin.domain.repository.GetRemoteCategories
import com.thezayin.framework.remote.SupabaseApiClient
import com.thezayin.framework.utils.Response
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRemoteCategoriesImpl(
    private val supabaseApiClient: SupabaseApiClient,
    private val arabicDao: com.thezayin.databases.dao.ArabicDao,
    private val bridalDao: com.thezayin.databases.dao.BridalDao,
    private val classicDao: com.thezayin.databases.dao.ClassicDao,
    private val fingerDao: com.thezayin.databases.dao.FingerDao,
    private val footDao: com.thezayin.databases.dao.FootDao,
    private val indianDao: com.thezayin.databases.dao.IndianDao,
    private val indoDao: com.thezayin.databases.dao.IndoDao,
    private val moroccanDao: com.thezayin.databases.dao.MoroccanDao,
    private val pakistaniDao: com.thezayin.databases.dao.PakistaniDao,
    private val tattooDao: com.thezayin.databases.dao.TattooDao,
    private val tikkiDao: com.thezayin.databases.dao.TikkiDao
) : GetRemoteCategories {
    override fun getArabicImages(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val listImage =
                supabaseApiClient.sup().from("arabic_images").select().decodeList<ArabicModel>()
            if (listImage.isEmpty()) {
                emit(Response.Success(false))
                return@flow
            } else {
                arabicDao.addImages(listImage)
                emit(Response.Success(true))

            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getBridalImages(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val listImage =
                supabaseApiClient.sup().from("bridal_images").select().decodeList<BridalModel>()
            if (listImage.isEmpty()) {
                emit(Response.Success(false))
                return@flow
            } else {
                bridalDao.addImages(listImage)
                emit(Response.Success(true))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getClassicImages(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val listImage =
                supabaseApiClient.sup().from("classic_images").select().decodeList<ClassicModel>()
            if (listImage.isEmpty()) {
                emit(Response.Success(false))
                return@flow
            } else {
                classicDao.addImages(listImage)
                emit(Response.Success(true))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getFingerImages(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val listImage =
                supabaseApiClient.sup().from("finger_images").select().decodeList<FingerModel>()
            if (listImage.isEmpty()) {
                emit(Response.Success(false))
                return@flow
            } else {
                fingerDao.addImages(listImage)
                emit(Response.Success(true))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getFootImages(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val listImage =
                supabaseApiClient.sup().from("foot_images").select().decodeList<FootModel>()
            if (listImage.isEmpty()) {
                emit(Response.Success(false))
                return@flow
            } else {
                footDao.addImages(listImage)
                emit(Response.Success(true))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getIndianImages(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val listImage =
                supabaseApiClient.sup().from("indian_images").select().decodeList<IndianModel>()
            if (listImage.isEmpty()) {
                emit(Response.Success(false))
                return@flow
            } else {
                indianDao.addImages(listImage)
                emit(Response.Success(true))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getIndoImages(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val listImage =
                supabaseApiClient.sup().from("indo_images").select().decodeList<IndoModel>()
            if (listImage.isEmpty()) {
                emit(Response.Success(false))
                return@flow
            } else {
                indoDao.addImages(listImage)
                emit(Response.Success(true))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getMoroccanImages(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val listImage =
                supabaseApiClient.sup().from("moroccan_images").select().decodeList<MoroccanModel>()
            if (listImage.isEmpty()) {
                emit(Response.Success(false))
                return@flow
            } else {
                moroccanDao.addImages(listImage)
                emit(Response.Success(true))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getPakistaniImages(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val listImage =
                supabaseApiClient.sup().from("pakistani_images").select()
                    .decodeList<PakistaniModel>()
            if (listImage.isEmpty()) {
                emit(Response.Success(false))
                return@flow
            } else {
                pakistaniDao.addImages(listImage)
                emit(Response.Success(true))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getTattooImages(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val listImage =
                supabaseApiClient.sup().from("tattoo_images").select().decodeList<TattooModel>()
            if (listImage.isEmpty()) {
                emit(Response.Success(false))
                return@flow
            } else {
                tattooDao.addImages(listImage)
                emit(Response.Success(true))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

    override fun getTikkiImages(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val listImage =
                supabaseApiClient.sup().from("tikki_images").select().decodeList<TikkiModel>()
            if (listImage.isEmpty()) {
                emit(Response.Success(false))
                return@flow
            } else {
                tikkiDao.addImages(listImage)
                emit(Response.Success(true))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}