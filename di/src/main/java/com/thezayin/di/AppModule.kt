package com.thezayin.di

import com.thezayin.ads.GoogleManager
import com.thezayin.ads.ump.ConsentManager
import com.thezayin.categories.data.CategoryRepositoryImpl
import com.thezayin.categories.data.HomeCategoryRepositoryImpl
import com.thezayin.categories.domain.repository.CategoryRepository
import com.thezayin.categories.domain.repository.HomeCategoryRepository
import com.thezayin.categories.domain.usecase.GetCategories
import com.thezayin.categories.domain.usecase.GetCategoriesImpl
import com.thezayin.categories.domain.usecase.HomeCategories
import com.thezayin.categories.domain.usecase.HomeCategoriesImpl
import com.thezayin.categories.presentation.CategoriesViewModel
import com.thezayin.common.viewmodel.MainViewModel
import com.thezayin.databases.provideDatabase
import com.thezayin.databases.provideImageDao
import com.thezayin.databases.provideRemoteKeysDao
import com.thezayin.home.data.paging.ImageRemoteMediator
import com.thezayin.home.data.repository.HomeImagesRepositoryImpl
import com.thezayin.home.domain.repository.HomeImagesRepository
import com.thezayin.home.domain.usecase.GetHomeImages
import com.thezayin.home.domain.usecase.GetHomeImagesImpl
import com.thezayin.home.presentation.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import com.thezayin.home.data.remote.SupabaseApiClient

val adModule = module {
    single { ConsentManager(androidContext()) }
    single { GoogleManager(androidContext(), get()) }
    viewModelOf(::MainViewModel)
}

val appModule = module {

}

val categoryModule = module {
    singleOf(::HomeCategoryRepositoryImpl) bind HomeCategoryRepository::class
    singleOf(::CategoryRepositoryImpl) bind CategoryRepository::class
    singleOf(::GetCategoriesImpl) bind GetCategories::class
    singleOf(::HomeCategoriesImpl) bind HomeCategories::class
    viewModelOf(::CategoriesViewModel)
}

val databaseModule = module {
    single { provideDatabase(androidContext()) }
    single { provideImageDao(get()) }
    single { provideRemoteKeysDao(get()) }
}

val homeModule = module {
    singleOf(::SupabaseApiClient)
    single { ImageRemoteMediator(get(), get()) }
    singleOf(::HomeImagesRepositoryImpl) bind HomeImagesRepository::class
    singleOf(::GetHomeImagesImpl) bind GetHomeImages::class
    viewModelOf(::HomeViewModel)
}