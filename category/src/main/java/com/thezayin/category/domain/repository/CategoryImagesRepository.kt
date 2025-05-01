package com.thezayin.category.domain.repository

import com.thezayin.framework.model.Image

/**
 * Repository interface for fetching images filtered by category.
 *
 * This interface defines the contract for repositories that provide images based on a specific category.
 * Implementations of this interface will be responsible for fetching images and filtering them by category ID.
 */
interface CategoryImagesRepository {

    /**
     * Fetches a list of images that belong to a specified category.
     *
     * @param categoryId The ID of the category used to filter the images.
     * @return A list of [Image] objects that belong to the specified category.
     */
    fun getImagesForCategory(categoryId: Int): List<Image>
}
