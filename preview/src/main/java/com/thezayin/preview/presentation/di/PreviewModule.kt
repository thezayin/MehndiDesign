package com.thezayin.preview.presentation.di

import com.thezayin.preview.data.repository.ImageFileRepositoryImpl
import com.thezayin.preview.domain.repository.ImageFileRepository
import com.thezayin.preview.domain.usecase.DownloadImageUseCase
import com.thezayin.preview.domain.usecase.DownloadImageUseCaseImpl
import com.thezayin.preview.domain.usecase.GetDownloadedImageUriUseCase
import com.thezayin.preview.domain.usecase.GetDownloadedImageUriUseCaseImpl
import com.thezayin.preview.domain.usecase.GetSelectedImageUseCase
import com.thezayin.preview.domain.usecase.GetSelectedImageUseCaseImpl
import com.thezayin.preview.domain.usecase.IsImageLikedUseCase
import com.thezayin.preview.domain.usecase.IsImageLikedUseCaseImpl
import com.thezayin.preview.domain.usecase.ShareImageUseCase
import com.thezayin.preview.domain.usecase.ShareImageUseCaseImpl
import com.thezayin.preview.domain.usecase.ToggleLikeUseCase
import com.thezayin.preview.domain.usecase.ToggleLikeUseCaseImpl
import com.thezayin.preview.presentation.PreviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val previewModule = module {
    singleOf(::GetDownloadedImageUriUseCaseImpl) bind GetDownloadedImageUriUseCase::class
    singleOf(::GetSelectedImageUseCaseImpl) bind GetSelectedImageUseCase::class
    singleOf(::DownloadImageUseCaseImpl) bind DownloadImageUseCase::class
    singleOf(::IsImageLikedUseCaseImpl) bind IsImageLikedUseCase::class
    singleOf(::ImageFileRepositoryImpl) bind ImageFileRepository::class
    singleOf(::ToggleLikeUseCaseImpl) bind ToggleLikeUseCase::class
    singleOf(::ShareImageUseCaseImpl) bind ShareImageUseCase::class
    viewModelOf(::PreviewViewModel)
}