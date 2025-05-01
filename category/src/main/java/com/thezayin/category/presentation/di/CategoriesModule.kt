package com.thezayin.category.presentation.di

import com.thezayin.category.data.repository.CategoryImagesRepositoryImpl
import com.thezayin.category.domain.repository.CategoryImagesRepository
import com.thezayin.category.domain.usecase.GetImagesForCategoryUseCase
import com.thezayin.category.domain.usecase.GetImagesForCategoryUseCaseImpl
import com.thezayin.category.presentation.CategoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val categoriesModule = module {
    singleOf(::GetImagesForCategoryUseCaseImpl) bind GetImagesForCategoryUseCase::class
    singleOf(::CategoryImagesRepositoryImpl) bind CategoryImagesRepository::class
    viewModelOf(::CategoryViewModel)
}