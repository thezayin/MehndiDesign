package com.thezayin.presentation.di

import com.thezayin.data.repository.CategoryRepositoryImpl
import com.thezayin.domain.repository.CategoryRepository
import com.thezayin.domain.usecase.GetCategories
import com.thezayin.domain.usecase.GetCategoriesImpl
import com.thezayin.presentation.CategoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val categoryModule = module {
    viewModelOf(::CategoryViewModel)
    singleOf(::CategoryRepositoryImpl) bind CategoryRepository::class
    singleOf(::GetCategoriesImpl) bind GetCategories::class
}