package com.thezayin.domain.usecase

import com.thezayin.domain.model.PreviewMenu
import com.thezayin.domain.repository.FavoriteMenuRepository
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.flow.Flow

/**
 * Use case interface for retrieving the list of favorite menu items.
 * It is represented as a suspend function that returns a [Flow] of [Response] containing a list of [PreviewMenu].
 */
interface GetFavouriteMenuList : suspend () -> Flow<Response<List<PreviewMenu>>>

/**
 * Implementation of the [GetFavouriteMenuList] interface.
 * It interacts with the [FavoriteMenuRepository] to fetch the favorite menu items.
 *
 * @param repository The repository to fetch the favorite menu items from.
 */
class GetFavouriteMenuListImpl(private val repository: FavoriteMenuRepository) :
    GetFavouriteMenuList {

    /**
     * Invokes the use case to retrieve the list of favorite menu items.
     *
     * @return A [Flow] that emits [Response] objects containing a list of [PreviewMenu].
     */
    override suspend fun invoke(): Flow<Response<List<PreviewMenu>>> {
        return repository.getMenuItems()
    }
}