package com.thezayin.domain.repository

import com.thezayin.framework.utils.Responsee
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for handling image saving operations.
 * Provides functionality to save an image by its URL.
 */
interface SaveImageRepository {
    /**
     * Saves an image from the provided URL.
     * The result is wrapped in a [Flow] of [Responsee], indicating the status of the operation
     * (loading, success, or error) and contains a message with the result.
     *
     * @param url The URL of the image to be saved.
     * @return A [Flow] that emits [Responsee] objects containing a result message.
     */
    fun saveImage(url: String): Flow<Responsee<String>>
}