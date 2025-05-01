package com.thezayin.homes.domain.usecase

import androidx.paging.PagingData
import com.thezayin.framework.model.Image
import com.thezayin.framework.utils.Response
import com.thezayin.homes.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for fetching a paginated list of [Image] objects for the home screen.
 *
 * This defines the contract for retrieving images in a paginated manner.
 * It abstracts the logic of interacting with the repository and exposes a
 * flow that emits either a loading state, the paginated data, or an error.
 *
 * @return A [Flow] emitting [Response.Loading] while fetching,
 *         [Response.Success] with the flow of images in paginated format on success,
 *         or [Response.Error] if an error occurs during data fetch.
 */
interface GetImagesUseCase : suspend () -> Flow<Response<Flow<PagingData<Image>>>>


/**
 * Default implementation of [GetImagesUseCase].
 *
 * This implementation delegates the actual data retrieval to the [HomeRepository],
 * allowing the repository to manage how images are fetched (e.g., from local storage
 * or remote source).
 *
 * @param homeRepository The repository responsible for fetching image data.
 */
class GetImagesUseCaseImpl(
    private val homeRepository: HomeRepository
) : GetImagesUseCase {

    /**
     * Executes the use case to retrieve the paginated images.
     *
     * This simply calls [HomeRepository.fetchImages] and returns the flow of [Response]
     * containing a flow of paginated images.
     */
    override suspend fun invoke(): Flow<Response<Flow<PagingData<Image>>>> =
        homeRepository.fetchImages()
}