package com.thezayin.domain.usecase

import com.thezayin.domain.model.PreviewMenu
import com.thezayin.domain.repository.PreviewMenuRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

/**
 * Use case interface for retrieving the list of preview menu items.
 * It is represented as a suspend function that returns a [Flow] of [Response] containing a list of [PreviewMenu].
 */
interface PreviewItems : suspend () -> Flow<Response<List<PreviewMenu>>>

/**
 * Implementation of the [PreviewItems] interface.
 * It interacts with the [PreviewMenuRepository] to fetch the preview menu items.
 *
 * @param repository The repository to fetch the preview menu items from.
 */
class PreviewItemsImpl(private val repository: PreviewMenuRepository) : PreviewItems {

    /**
     * Invokes the use case to retrieve the list of preview menu items.
     *
     * @return A [Flow] that emits [Response] objects containing a list of [PreviewMenu].
     */
    override suspend fun invoke(): Flow<Response<List<PreviewMenu>>> = repository.getPreviewMenu()
}