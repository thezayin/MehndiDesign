package com.thezayin.preview.data.repository

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import coil.ImageLoader
import coil.request.ImageRequest
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.gson.Gson
import com.thezayin.framework.model.Image
import com.thezayin.framework.session.image.ImageSession
import com.thezayin.framework.utils.saveImageToExternalPrivate
import com.thezayin.preview.domain.repository.ImageFileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import java.io.IOException


class ImageFileRepositoryImpl(
    private val session: ImageSession,
    private val context: Context
) : ImageFileRepository {

    /**
     * Generate filename for liked image.
     * Filename follows the pattern `liked_<image_id>.jpg`.
     *
     * @param image The [Image] object for which filename is generated.
     * @return A string representing the filename for the liked image.
     */
    private fun filenameFor(image: Image) = "liked_${image.id}.jpg"

    /**
     * Get the directory where liked images are stored. If the directory does not exist, it will be created.
     *
     * @return A [File] object representing the "liked_images" directory.
     */
    private fun likedDir(): File = File(context.getExternalFilesDir(null), "liked_images").apply {
        if (!exists()) {
            Timber.d("Creating liked_images directory at $absolutePath")
            mkdirs()

            // Create .nomedia file to prevent media scanner from indexing this directory
            File(this, ".nomedia").takeIf { !it.exists() }?.also {
                Timber.d("Creating .nomedia in liked_images")
                it.createNewFile()
            }
        }
    }

    /**
     * Checks if an image is marked as liked.
     *
     * @param image The [Image] object to check.
     * @return A boolean indicating if the image is liked.
     */
    override fun isLiked(image: Image): Boolean {
        val file = File(likedDir(), filenameFor(image))
        val exists = file.exists()
        Timber.d("isLiked(${image.id}): file=${file.absolutePath} exists=$exists")
        return exists
    }

    /**
     * Retrieve the currently selected image from the session.
     *
     * @return The selected [Image] or null if no image is selected.
     */
    override fun getSelectedImage(): Image? {
        val selected = session.getSelected()
        Timber.d("getSelectedImage(): $selected")
        return selected
    }

    /**
     * Toggles the "liked" status of an image. If the image is already liked, it will be removed.
     * If the image is not liked, it will be downloaded and saved.
     *
     * @param image The [Image] object to toggle.
     * @return Boolean indicating the success of the operation.
     */
    override suspend fun toggleLike(image: Image): Boolean {
        val dir = likedDir()
        val file = File(dir, filenameFor(image))
        val jsonFile = File(dir, filenameFor(image).replace(".jpg", ".json"))

        return if (file.exists()) {
            Timber.d("toggleLike(): currently liked, deleting ${file.absolutePath}")
            val deletedImage = file.delete()
            val deletedJson = jsonFile.delete()
            Timber.d("toggleLike(): delete result = image? $deletedImage  json? $deletedJson")
            deletedImage && deletedJson
        } else {
            Timber.d("toggleLike(): not liked, fetching & saving ${image.image_url}")

            try {
                // Fetch the image using Coil
                val loader = ImageLoader(context)
                val req = ImageRequest.Builder(context).data(image.image_url).build()
                val result = loader.execute(req)
                val drawable = result.drawable as? BitmapDrawable
                    ?: throw IllegalStateException("Could not decode image")
                val bmp = drawable.bitmap

                // Save image to external storage
                val savedFile = context.saveImageToExternalPrivate(bmp, subDir = "liked_images", filename = filenameFor(image))
                Timber.d("toggleLike(): saved image to ${savedFile.absolutePath}")

                // Save image metadata (JSON)
                val gson = Gson()
                val json = gson.toJson(image)
                jsonFile.writeText(json)
                Timber.d("toggleLike(): saved metadata to ${jsonFile.absolutePath}")

                true
            } catch (e: Exception) {
                // Log error to Firebase Crashlytics
                FirebaseCrashlytics.getInstance().recordException(e)
                Timber.e(e, "Error saving image or metadata")
                false
            }
        }
    }

    /**
     * Saves an image to the device's media store and returns the URI of the saved image.
     *
     * @param image The [Image] object to be saved.
     * @return The URI of the saved image in the MediaStore.
     * @throws IOException If an error occurs during the image saving process.
     */
    override suspend fun saveImage(image: Image): Uri = withContext(Dispatchers.IO) {
        // Load the image using Coil
        val loader = ImageLoader(context)
        val req = ImageRequest.Builder(context).data(image.image_url).build()
        val result = loader.execute(req)
        val bmp = (result.drawable as? BitmapDrawable)?.bitmap
            ?: throw IOException("Failed to decode image")

        // Prepare the values to insert into the MediaStore
        val values = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, filenameFor(image))
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, "${Environment.DIRECTORY_PICTURES}/MehndiDesign")
                put(MediaStore.MediaColumns.IS_PENDING, 1)
            }
        }

        // Insert the image into the MediaStore
        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            ?: throw IOException("Failed to create MediaStore record.")

        // Write the bitmap to the output stream
        resolver.openOutputStream(uri)?.use { out ->
            if (!bmp.compress(Bitmap.CompressFormat.JPEG, 90, out))
                throw IOException("Failed to save bitmap.")
        }

        // Mark the image as no longer pending if on Android Q or above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.clear()
            values.put(MediaStore.MediaColumns.IS_PENDING, 0)
            resolver.update(uri, values, null, null)
        }

        uri
    }

    /**
     * Retrieves the URI of an image from the MediaStore based on its filename.
     *
     * @param image The [Image] object for which the URI is needed.
     * @return The URI of the image if it exists, or null if not found.
     */
    override fun getImageUri(image: Image): Uri? {
        val filename = filenameFor(image)
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val sel = "${MediaStore.Images.Media.DISPLAY_NAME}=?"
        val args = arrayOf(filename)

        context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection, sel, args, null
        )?.use { cursor ->
            if (cursor.moveToFirst()) {
                val id = cursor.getLong(0)
                return Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id.toString())
            }
        }
        return null
    }
}
