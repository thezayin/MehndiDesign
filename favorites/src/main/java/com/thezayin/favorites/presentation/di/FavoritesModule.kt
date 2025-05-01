package com.thezayin.favorites.presentation.di

import com.thezayin.favorites.data.FavoriteRepositoryImpl
import com.thezayin.favorites.domain.repository.FavoriteRepository
import com.thezayin.favorites.domain.usecase.GetFavoritesUseCase
import com.thezayin.favorites.domain.usecase.GetFavoritesUseCaseImpl
import com.thezayin.favorites.domain.usecase.RemoveFavoriteUseCase
import com.thezayin.favorites.domain.usecase.RemoveFavoriteUseCaseImpl
import com.thezayin.favorites.presentation.FavoritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val favoritesModule = module {
    singleOf(::FavoriteRepositoryImpl) bind FavoriteRepository::class
    singleOf(::GetFavoritesUseCaseImpl) bind GetFavoritesUseCase::class
    singleOf(::RemoveFavoriteUseCaseImpl) bind RemoveFavoriteUseCase::class
    viewModelOf(::FavoritesViewModel)
}
