package com.thezayin.presentation.di

import com.thezayin.data.repository.HomeCategoryRepositoryImpl
import com.thezayin.data.repository.ImageRepositoryImpl
import com.thezayin.data.repository.RemoteRepositoryImpl
import com.thezayin.domain.repository.HomeCategoryRepository
import com.thezayin.domain.repository.ImageRepository
import com.thezayin.domain.repository.RemoteRepository
import com.thezayin.domain.usecase.FetchRemote
import com.thezayin.domain.usecase.FetchRemoteImpl
import com.thezayin.domain.usecase.GetHomeImages
import com.thezayin.domain.usecase.GetHomeImagesImpl
import com.thezayin.domain.usecase.HomeCategories
import com.thezayin.domain.usecase.HomeCategoriesImpl
import com.thezayin.presentation.HomeViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Koin module for providing dependencies related to the Home feature.
 */
val homeModule = module {

    // Provide implementation for RemoteRepository
    singleOf(::RemoteRepositoryImpl) bind RemoteRepository::class

    // Provide implementation for ImageRepository
    singleOf(::ImageRepositoryImpl) bind ImageRepository::class

    // Provide implementation for HomeCategoryRepository
    singleOf(::HomeCategoryRepositoryImpl) bind HomeCategoryRepository::class

    // Provide implementation for GetHomeImages use case
    singleOf(::GetHomeImagesImpl) bind GetHomeImages::class

    // Provide implementation for HomeCategories use case
    singleOf(::HomeCategoriesImpl) bind HomeCategories::class

    // Provide implementation for FetchRemote use case
    singleOf(::FetchRemoteImpl) bind FetchRemote::class

    // Provide ViewModel for Home feature
    singleOf(::HomeViewModel)
}