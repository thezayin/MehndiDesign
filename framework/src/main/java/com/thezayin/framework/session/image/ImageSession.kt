package com.thezayin.framework.session.image

import com.thezayin.framework.model.Image

/**
 * This interface is used to manage the image session.
 * It allows selecting an image and retrieving the last-selected image.
 */
interface ImageSession {
    /** Remember which image the user tapped. */
    fun select(image: Image)

    /** Fetch the last-selected image (or null if none). */
    fun getSelected(): Image?
}
