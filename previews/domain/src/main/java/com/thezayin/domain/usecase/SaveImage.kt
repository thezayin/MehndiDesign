package com.thezayin.domain.usecase

import com.thezayin.framework.utils.Response
import com.thezayin.domain.repository.SaveImageRepository
import kotlinx.coroutines.flow.Flow


/**
 * Use case interface for saving an image.
 * It takes an image URL as a parameter and returns a [Flow] of [Response] containing the result message.
 */
interface SaveImage : suspend (String) -> Flow<Response<String>>

/**
 * Implementation of the [SaveImage] interface.
 * It interacts with the [SaveImageRepository] to save the image.
 *
 * @param repository The repository responsible for saving the image.
 */
class SaveImageImpl(private val repository: SaveImageRepository) : SaveImage {

    /**
     * Invokes the use case to save an image with the given URL.
     *
     * @param url The URL of the image to be saved.
     * @return A [Flow] that emits [Response] objects containing the result message.
     */
    override suspend fun invoke(url: String): Flow<Response<String>> =
        repository.saveImage(url)
}