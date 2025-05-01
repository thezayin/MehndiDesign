package com.thezayin.preview.domain.usecase

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.core.content.FileProvider
import coil.ImageLoader
import coil.request.ImageRequest
import com.thezayin.framework.model.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

/**
 * Fetches the image over the network, writes it to a temp cache file,
 * and returns a content:// Uri suitable for sharing.
 */
interface ShareImageUseCase {
    suspend operator fun invoke(image: Image): Uri
}

class ShareImageUseCaseImpl(
    private val context: Context
) : ShareImageUseCase {

    override suspend fun invoke(image: Image): Uri = withContext(Dispatchers.IO) {
        // 1) load bitmap via Coil
        val loader  = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(image.image_url)
            .build()
        val result  = loader.execute(request)
        val bmp     = (result.drawable as? BitmapDrawable)
            ?.bitmap
            ?: throw IllegalStateException("Could not decode image")

        // 2) write into cache/share_images
        val dir = File(context.cacheDir, "share_images").apply {
            if (!exists()) mkdirs()
        }
        val file = File(dir, "share_${image.id}.jpg").apply {
            outputStream().use { out -> bmp.compress(Bitmap.CompressFormat.JPEG, 90, out) }
        }

        // 3) get content:// Uri via your FileProvider
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }
}