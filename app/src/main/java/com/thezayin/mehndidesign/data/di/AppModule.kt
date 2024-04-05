package com.thezayin.mehndidesign.data.di

import com.thezayin.mehndidesign.data.paging.UnsplashRemoteMediator
import com.thezayin.mehndidesign.data.remote.UnsplashApi
import com.thezayin.mehndidesign.data.repository.RepositoryImpl
import com.thezayin.mehndidesign.domain.repository.Repository
import com.thezayin.mehndidesign.domain.usecase.GetAllImagesUseCase
import com.thezayin.mehndidesign.domain.usecase.GetAllImagesUseCaseImpl
import com.thezayin.mehndidesign.domain.usecase.SearchUseCase
import com.thezayin.mehndidesign.domain.usecase.SearchUseCaseImpl
import com.thezayin.mehndidesign.presentation.home.viewmodel.HomeViewModel
import com.thezayin.mehndidesign.presentation.search.viewmodel.SearchViewModel
import com.thezayin.mehndidesign.presentation.topdrawer.domain.repository.DrawerRepository
import com.thezayin.mehndidesign.presentation.topdrawer.domain.usecase.DrawerUseCase
import com.thezayin.mehndidesign.presentation.topdrawer.domain.usecase.DrawerUseCaseImpl
import com.thezayin.mehndidesign.presentation.topdrawer.presentation.viewmodel.DrawerViewModel
import com.thezayin.mehndidesign.presentation.topdrawer.data.repository.DrawerRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::UnsplashApi)
    single { provideDatabase(androidContext()) }
    single { provideUnsplashImageDao(get()) }
    single { provideUnsplashRemoteKeysDao(get()) }
    single { UnsplashRemoteMediator(get(), get()) }

    factoryOf(::RepositoryImpl) bind Repository::class
    factoryOf(::GetAllImagesUseCaseImpl) bind GetAllImagesUseCase::class
    factoryOf(::SearchUseCaseImpl) bind SearchUseCase::class
    viewModelOf(::HomeViewModel)
    viewModelOf(::SearchViewModel)
    factoryOf(::DrawerRepositoryImpl) bind DrawerRepository::class
    factoryOf(::DrawerUseCaseImpl) bind DrawerUseCase::class
    viewModelOf(::DrawerViewModel)
}