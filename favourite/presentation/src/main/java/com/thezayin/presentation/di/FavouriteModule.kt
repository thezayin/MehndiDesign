package com.thezayin.presentation.di

import com.thezayin.data.repository.FavouriteRepositoryImpl
import com.thezayin.domain.repository.FavouriteRepository
import com.thezayin.domain.usecase.DeleteImage
import com.thezayin.domain.usecase.DeleteImageImpl
import com.thezayin.domain.usecase.FavouriteImages
import com.thezayin.domain.usecase.FavouriteImagesImpl
import com.thezayin.domain.usecase.InsertImage
import com.thezayin.domain.usecase.InsertImageImpl
import com.thezayin.presentation.FavouriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

// Dependency injection module for the Favourite feature
val favouriteModule = module {

    // Provides an instance of FavouriteRepositoryImpl bound to FavouriteRepository
    singleOf(::FavouriteRepositoryImpl) bind FavouriteRepository::class

    // Provides an instance of FavouriteImagesImpl bound to FavouriteImages
    singleOf(::FavouriteImagesImpl) bind FavouriteImages::class

    // Provides an instance of InsertImageImpl bound to InsertImage
    singleOf(::InsertImageImpl) bind InsertImage::class

    // Provides an instance of DeleteImageImpl bound to DeleteImage
    singleOf(::DeleteImageImpl) bind DeleteImage::class

    // Provides an instance of FavouriteViewModel
    viewModelOf(::FavouriteViewModel)
}
