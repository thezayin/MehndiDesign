package com.thezayin.data.repository

import com.thezayin.databases.models.ImageModel
import com.thezayin.framework.utils.Response
import com.thezayin.databases.dao.HomeImageDao
import com.thezayin.framework.remote.SupabaseApiClient
import com.thezayin.domain.repository.RemoteRepository
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementation of the RemoteRepository interface.
 *
 * This repository is responsible for fetching images from a remote data source
 * and storing them in a local database.
 *
 * @param supabaseApiClient Client for interacting with Supabase API.
 * @param dao Data Access Object for managing image data in the local database.
 */
class RemoteRepositoryImpl(
    private val supabaseApiClient: SupabaseApiClient,

    private val dao: HomeImageDao
) : RemoteRepository {

    /**
     * Fetches all images from the remote source and updates the local database.
     *
     * @return A Flow emitting a Response indicating whether the images were successfully fetched and stored.
     */
    override fun getAllImages(): Flow<Response<Boolean>> = flow {
        try {
            // Emit loading state while fetching data.
            emit(Response.Loading)

            // Fetch images from the remote source using Supabase API.
            val fetchedImages = supabaseApiClient.sup()
                .from("mehndi_images")
                .select()
                .decodeList<ImageModel>()

            // Check if any images were fetched.
            if (fetchedImages.isEmpty()) {
                // Emit success with 'false' if no images were found.
                emit(Response.Success(false))
            } else {
                // Store the fetched images in the local database and emit success with 'true'.
                dao.insertImages(fetchedImages)
                emit(Response.Success(true))
            }
        } catch (exception: Exception) {
            // Emit error state if an exception occurs.
            emit(Response.Error(exception.localizedMessage ?: "An error occurred"))
        }
    }
}