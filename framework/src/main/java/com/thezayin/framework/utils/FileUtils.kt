package com.thezayin.framework.utils

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

fun Context.saveImageToExternalPrivate(
    bitmap: Bitmap,
    subDir: String = "liked_images",
    filename: String
): File {
    val imagesDir = File(getExternalFilesDir(null), subDir).apply {
        if (!exists()) mkdirs()
        File(this, ".nomedia").takeIf { !it.exists() }?.createNewFile()
    }

    val outFile = File(imagesDir, filename)
    FileOutputStream(outFile).use { fos ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos)
    }
    return outFile
}
