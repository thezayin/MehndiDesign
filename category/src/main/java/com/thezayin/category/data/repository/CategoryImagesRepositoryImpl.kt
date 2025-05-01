package com.thezayin.category.data.repository

import com.thezayin.category.domain.repository.CategoryImagesRepository
import com.thezayin.framework.model.Image
import com.thezayin.framework.pref.PrefManager
import timber.log.Timber
import com.google.firebase.crashlytics.FirebaseCrashlytics

/**
 * Implementation of [CategoryImagesRepository] which retrieves images filtered by category.
 *
 * This class retrieves all images from the [PrefManager] and filters them by a specific category ID.
 * The filtering process ensures only the images belonging to the requested category are returned.
 *
 * @param pref [PrefManager] instance for accessing locally stored images.
 */
class CategoryImagesRepositoryImpl(
    private val pref: PrefManager
) : CategoryImagesRepository {

    /**
     * Fetches a list of images filtered by the provided category ID.
     *
     * This function fetches all the images from the [PrefManager], filters them based on the category_id,
     * and returns a list of images belonging to the specified category.
     *
     * @param categoryId The category ID used to filter the images.
     * @return A list of [Image] objects that belong to the specified category.
     */
    override fun getImagesForCategory(categoryId: Int): List<Image> {
        return try {
            // Log fetching process for better tracing
            Timber.tag("CategoryImagesRepo").d("Fetching images for category ID: $categoryId")

            // Fetch images from PrefManager and filter by category_id
            val images = pref.getImages()
                .filter { it.category_id == categoryId }

            // Log success with the count of images fetched
            Timber.tag("CategoryImagesRepo").d("Fetched ${images.size} images for category ID: $categoryId")

            images
        } catch (e: Exception) {
            Timber.tag("CategoryImagesRepo").e(e, "Error fetching images for category ID: $categoryId")
            FirebaseCrashlytics.getInstance().recordException(e)
            emptyList<Image>().also {
                FirebaseCrashlytics.getInstance().log("Failed to fetch images for category ID: $categoryId")
            }
        }
    }
}
