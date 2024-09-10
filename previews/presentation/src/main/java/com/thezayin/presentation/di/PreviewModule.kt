package com.thezayin.presentation.di

import com.thezayin.data.FavoriteMenuRepositoryImpl
import com.thezayin.data.PreviewMenuRepositoryImpl
import com.thezayin.data.SaveImageRepositoryImpl
import com.thezayin.domain.repository.FavoriteMenuRepository
import com.thezayin.domain.repository.PreviewMenuRepository
import com.thezayin.domain.repository.SaveImageRepository
import com.thezayin.domain.usecase.GetFavouriteMenuList
import com.thezayin.domain.usecase.GetFavouriteMenuListImpl
import com.thezayin.domain.usecase.PreviewItems
import com.thezayin.domain.usecase.PreviewItemsImpl
import com.thezayin.domain.usecase.SaveImage
import com.thezayin.domain.usecase.SaveImageImpl
import com.thezayin.presentation.PreviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val previewModule = module {
    viewModelOf(::PreviewViewModel)
    singleOf(::SaveImageImpl) bind SaveImage::class
    singleOf(::PreviewItemsImpl) bind PreviewItems::class
    singleOf(::GetFavouriteMenuListImpl) bind GetFavouriteMenuList::class
    singleOf(::FavoriteMenuRepositoryImpl) bind FavoriteMenuRepository::class
    singleOf(::SaveImageRepositoryImpl) bind SaveImageRepository::class
    singleOf(::PreviewMenuRepositoryImpl) bind PreviewMenuRepository::class
}