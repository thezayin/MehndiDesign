package com.thezayin.categories.data.remote

import android.util.Log
import com.thezayin.categories.data.local.dao.ArabicDao
import com.thezayin.categories.data.local.dao.BridalDao
import com.thezayin.categories.data.local.dao.ClassicDao
import com.thezayin.categories.data.local.dao.FingerDao
import com.thezayin.categories.data.local.dao.FootDao
import com.thezayin.categories.data.local.dao.IndianDao
import com.thezayin.categories.data.local.dao.IndoDao
import com.thezayin.categories.data.local.dao.MoroccanDao
import com.thezayin.categories.data.local.dao.PakistaniDao
import com.thezayin.categories.data.local.dao.TattooDao
import com.thezayin.categories.data.local.dao.TikkiDao
import com.thezayin.categories.domain.repository.GetRemoteCategories
import com.thezayin.common.remote.SupabaseApiClient
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
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRemoteCategoriesImpl(
    private val supabaseApiClient: SupabaseApiClient,
    private val arabicDao: ArabicDao,
    private val bridalDao: BridalDao,
    private val classicDao: ClassicDao,
    private val fingerDao: FingerDao,
    private val footDao: FootDao,
    private val indianDao: IndianDao,
    private val indoDao: IndoDao,
    private val moroccanDao: MoroccanDao,
    private val pakistaniDao: PakistaniDao,
    private val tattooDao: TattooDao,
    private val tikkiDao: TikkiDao
) : GetRemoteCategories {
    override fun getArabicImages(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val listImage =
                supabaseApiClient.sup().from("arabic_images").select().decodeList<ArabicModel>()
            Log.d("jejeArabicRepoCat", "getArabicImages: $listImage")
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
            Log.d("jejeBridal", "getBridalImages: $listImage")
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
            Log.d("jejeClassic", "getClassicImages: $listImage")
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
            Log.d("jejeFinger", "getFingerImages: $listImage")
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
            Log.d("jejeFoot", "getFootImages: $listImage")
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
            Log.d("jejeIndian", "getIndianImages: $listImage")
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
            Log.d("jejeIndo", "getIndoImages: $listImage")
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
            Log.d("jejeMoroccan", "getMoroccanImages: $listImage")
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
            Log.d("jejePakistani", "getPakistaniImages: $listImage")
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
            Log.d("jejeTattoo", "getTattooImages: $listImage")
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
            Log.d("jejeTikki", "getTikkiImages: $listImage")
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