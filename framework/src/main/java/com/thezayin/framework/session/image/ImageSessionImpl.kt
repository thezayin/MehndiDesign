package com.thezayin.framework.session.image

import com.thezayin.framework.model.Image

/**
 * This class implements the ImageSession interface to manage the image session.
 * It allows selecting an image and retrieving the last-selected image.
 */
class ImageSessionImpl : ImageSession {
    private var selected: Image? = null

    override fun select(image: Image) {
        selected = image
    }

    override fun getSelected(): Image? = selected
}
