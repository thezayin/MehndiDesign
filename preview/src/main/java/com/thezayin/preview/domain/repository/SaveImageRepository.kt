package com.thezayin.preview.domain.repository

import android.graphics.Bitmap
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

interface SaveImageRepository {
    fun saveImage(url: String):Flow<Response<String>>
}