package com.thezayin.upload.domain.repository

import android.net.Uri
import com.thezayin.upload.domain.model.ImageModel

interface AdminRepository {
    /**
     * Uploads a list of images to the specified table.
     *
     * @param uris List of image URIs to upload.
     * @param tableName The name of the table to insert image URLs into.
     * @return Result containing a list of inserted ImageModel objects or an error.
     */
    suspend fun uploadImages(uris: List<Uri>, tableName: String): Result<List<ImageModel>>
}