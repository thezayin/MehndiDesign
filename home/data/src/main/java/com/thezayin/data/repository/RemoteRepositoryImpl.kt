package com.thezayin.data.repository

import com.thezayin.entities.ImageModel
import com.thezayin.framework.utils.Response
import com.thezayin.data.local.dao.HomeImageDao
import com.thezayin.framework.remote.SupabaseApiClient
import com.thezayin.domain.repository.RemoteRepository
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteRepositoryImpl(
    private val supabaseApiClient: SupabaseApiClient,
    private val dao: HomeImageDao
) : RemoteRepository {
    override fun getAllImages(): Flow<Response<Boolean>> = flow {
        try {
            emit(Response.Loading)
            val listImage =
                supabaseApiClient.sup().from("mehndi_images").select().decodeList<ImageModel>()
            if (listImage.isEmpty()) {
                emit(Response.Success(false))
                return@flow
            } else {
                dao.insertImages(listImage)
                emit(Response.Success(true))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An error occurred"))
        }
    }
}