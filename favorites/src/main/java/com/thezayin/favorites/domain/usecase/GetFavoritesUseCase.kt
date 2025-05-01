package com.thezayin.favorites.domain.usecase

import com.thezayin.framework.model.Image
import com.thezayin.favorites.domain.repository.FavoriteRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

/**
 * Use case interface for retrieving the list of user's favorite images.
 * This operation fetches the images that the user has "liked" from the repository.
 */
fun interface GetFavoritesUseCase {

    /**
     * Fetches the current list of favorite images.
     * This method interacts with the [FavoriteRepository] to fetch the list of images
     * that the user has liked (saved to the local "liked_images" directory).
     *
     * @return A [Flow] emitting a [Response] containing a list of [Image] objects (the user's favorites).
     *         The response can be [Response.Loading], [Response.Success] with the list of images, or [Response.Error].
     */
    operator fun invoke(): Flow<Response<List<Image>>>
}

/**
 * Implementation of [GetFavoritesUseCase] that retrieves the list of favorites from the [FavoriteRepository].
 * This class is responsible for calling the repository method to fetch the list of "liked" images.
 */
class GetFavoritesUseCaseImpl(
    private val repo: FavoriteRepository
) : GetFavoritesUseCase {

    /**
     * Invokes the use case to get the list of favorite images.
     * This method interacts with the [FavoriteRepository] to get the data.
     *
     * @return A [Flow] emitting a [Response] with the list of favorite [Image] objects.
     */
    override fun invoke(): Flow<Response<List<Image>>> = repo.getFavorites()
}