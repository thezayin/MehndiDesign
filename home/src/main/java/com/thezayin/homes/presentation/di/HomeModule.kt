package com.thezayin.homes.presentation.di

import com.thezayin.homes.data.repository.HomeRepositoryImpl
import com.thezayin.homes.domain.repository.HomeRepository
import com.thezayin.homes.domain.usecase.GetCategoriesUseCase
import com.thezayin.homes.domain.usecase.GetCategoriesUseCaseImpl
import com.thezayin.homes.domain.usecase.GetImagesUseCase
import com.thezayin.homes.domain.usecase.GetImagesUseCaseImpl
import com.thezayin.homes.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
    singleOf(::HomeRepositoryImpl) bind HomeRepository::class
    singleOf(::GetImagesUseCaseImpl) bind GetImagesUseCase::class
    singleOf(::GetCategoriesUseCaseImpl) bind GetCategoriesUseCase::class
}