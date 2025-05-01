package com.thezayin.preview.domain.repository

import android.net.Uri
import com.thezayin.framework.model.Image

/**
 * Repository abstraction to fetch the currently selected image.
 */
interface ImageFileRepository {
    /**
     * Returns the user-selected image, or null if none.
     */
    fun getSelectedImage(): Image?
    /** true if we have previously saved this image under MODE_PRIVATE */

    fun isLiked(image: Image): Boolean
    /**
     * Toggle like state: if liked delete the private‐file, otherwise write it.
     * @return the new like state (true=now liked, false=now unliked)
     */
    suspend fun toggleLike(image: Image): Boolean

    /** download & persist into public Pictures/MehndiDesign; returns its Uri */
    suspend fun saveImage(image: Image): Uri

    fun getImageUri(image: Image): Uri?
}
