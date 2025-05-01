package com.thezayin.upload.presentation.di

import com.thezayin.upload.data.remote.AdminRemoteDataSource
import com.thezayin.upload.data.repository.AdminRepositoryImpl
import com.thezayin.upload.domain.repository.AdminRepository
import com.thezayin.upload.domain.usecase.UploadImagesUseCase
import com.thezayin.upload.domain.usecase.UploadImagesUseCaseImpl
import com.thezayin.upload.presentation.AdminUploadViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val uploadModule = module {
    viewModelOf(::AdminUploadViewModel)
    single { AdminRemoteDataSource(get(), get()) }
    singleOf(::AdminRepositoryImpl) bind AdminRepository::class
    singleOf(::UploadImagesUseCaseImpl) bind UploadImagesUseCase::class
}