package com.thezayin.category.domain.usecase

import com.thezayin.category.domain.repository.CategoryImagesRepository
import com.thezayin.framework.model.Image
import timber.log.Timber
import com.google.firebase.crashlytics.FirebaseCrashlytics

/**
 * Use case for fetching all images belonging to a specific category.
 *
 * This use case defines the contract and implementation for retrieving a list of [Image]
 * objects filtered by their category ID. It delegates the data retrieval to the
 * [CategoryImagesRepository] and provides a clean interface for the presentation layer.
 */
interface GetImagesForCategoryUseCase {

    /**
     * Retrieves the list of images associated with the given category ID.
     *
     * @param categoryId The ID of the category whose images are to be fetched.
     * @return A [List] of [Image] objects belonging to the specified category.
     */
    operator fun invoke(categoryId: Int): List<Image>
}

/**
 * Default implementation of [GetImagesForCategoryUseCase].
 *
 * This class executes the use case by invoking the corresponding repository method
 * and logs its execution. Any unexpected exceptions during data retrieval are recorded
 * to Firebase Crashlytics for monitoring.
 *
 * @param repo Repository responsible for accessing stored images.
 */
class GetImagesForCategoryUseCaseImpl(
    private val repo: CategoryImagesRepository
) : GetImagesForCategoryUseCase {

    /**
     * Executes the use case to fetch images for the specified category.
     *
     * @param categoryId The ID of the category whose images are to be fetched.
     * @return A [List] of [Image] objects filtered by category ID.
     */
    override operator fun invoke(categoryId: Int): List<Image> {
        return try {
            Timber.tag(TAG).d("Fetching images for categoryId=%d", categoryId)
            val images = repo.getImagesForCategory(categoryId)
            Timber.tag(TAG).d("Fetched %d images for categoryId=%d", images.size, categoryId)
            images
        } catch (e: Exception) {
            Timber.tag(TAG).e(e, "Error fetching images for categoryId=%d", categoryId)
            FirebaseCrashlytics.getInstance().recordException(e)
            emptyList()
        }
    }

    private companion object {
        private const val TAG = "GetImagesForCategory"
    }
}
