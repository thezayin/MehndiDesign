package com.thezayin.presentation.di

import com.thezayin.data.local.repository.GetCategoryImagesImpl
import com.thezayin.data.repository.GetRemoteCategoriesImpl
import com.thezayin.domain.repository.GetCategoryImages
import com.thezayin.domain.repository.GetRemoteCategories
import com.thezayin.domain.usecase.ArabicImages
import com.thezayin.domain.usecase.ArabicImagesImpl
import com.thezayin.domain.usecase.ArabicRemote
import com.thezayin.domain.usecase.ArabicRemoteImpl
import com.thezayin.domain.usecase.BridalImages
import com.thezayin.domain.usecase.BridalImagesImpl
import com.thezayin.domain.usecase.BridalRemote
import com.thezayin.domain.usecase.BridalRemoteImpl
import com.thezayin.domain.usecase.ClassicImages
import com.thezayin.domain.usecase.ClassicImagesImpl
import com.thezayin.domain.usecase.ClassicRemote
import com.thezayin.domain.usecase.ClassicRemoteImpl
import com.thezayin.domain.usecase.FingerImages
import com.thezayin.domain.usecase.FingerImagesImpl
import com.thezayin.domain.usecase.FingerRemote
import com.thezayin.domain.usecase.FingerRemoteImpl
import com.thezayin.domain.usecase.FootImages
import com.thezayin.domain.usecase.FootImagesImpl
import com.thezayin.domain.usecase.FootRemote
import com.thezayin.domain.usecase.FootRemoteImpl
import com.thezayin.domain.usecase.IndianImages
import com.thezayin.domain.usecase.IndianImagesImpl
import com.thezayin.domain.usecase.IndianRemote
import com.thezayin.domain.usecase.IndianRemoteImpl
import com.thezayin.domain.usecase.IndoImages
import com.thezayin.domain.usecase.IndoImagesImpl
import com.thezayin.domain.usecase.IndoRemote
import com.thezayin.domain.usecase.IndoRemoteImpl
import com.thezayin.domain.usecase.MoroccanImages
import com.thezayin.domain.usecase.MoroccanImagesImpl
import com.thezayin.domain.usecase.MoroccanRemote
import com.thezayin.domain.usecase.MoroccanRemoteImpl
import com.thezayin.domain.usecase.PakistaniImages
import com.thezayin.domain.usecase.PakistaniImagesImpl
import com.thezayin.domain.usecase.PakistaniRemote
import com.thezayin.domain.usecase.PakistaniRemoteImpl
import com.thezayin.domain.usecase.TattooImages
import com.thezayin.domain.usecase.TattooImagesImpl
import com.thezayin.domain.usecase.TattooRemote
import com.thezayin.domain.usecase.TattooRemoteImpl
import com.thezayin.domain.usecase.TikkiImages
import com.thezayin.domain.usecase.TikkiImagesImpl
import com.thezayin.domain.usecase.TikkiRemote
import com.thezayin.domain.usecase.TikkiRemoteImpl
import com.thezayin.presentation.CategoryImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val categoryImageModule = module {
    viewModel {
        CategoryImageViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
        )
    }
    singleOf(::GetRemoteCategoriesImpl) bind GetRemoteCategories::class
    singleOf(::GetCategoryImagesImpl) bind GetCategoryImages::class
    singleOf(::PakistaniImagesImpl) bind PakistaniImages::class
    singleOf(::MoroccanImagesImpl) bind MoroccanImages::class
    singleOf(::ClassicImagesImpl) bind ClassicImages::class
    singleOf(::ArabicImagesImpl) bind ArabicImages::class
    singleOf(::BridalImagesImpl) bind BridalImages::class
    singleOf(::FingerImagesImpl) bind FingerImages::class
    singleOf(::IndianImagesImpl) bind IndianImages::class
    singleOf(::TattooImagesImpl) bind TattooImages::class
    singleOf(::TikkiImagesImpl) bind TikkiImages::class
    singleOf(::IndoImagesImpl) bind IndoImages::class
    singleOf(::FootImagesImpl) bind FootImages::class

    singleOf(::PakistaniRemoteImpl) bind PakistaniRemote::class
    singleOf(::MoroccanRemoteImpl) bind MoroccanRemote::class
    singleOf(::ClassicRemoteImpl) bind ClassicRemote::class
    singleOf(::ArabicRemoteImpl) bind ArabicRemote::class
    singleOf(::BridalRemoteImpl) bind BridalRemote::class
    singleOf(::FingerRemoteImpl) bind FingerRemote::class
    singleOf(::IndianRemoteImpl) bind IndianRemote::class
    singleOf(::TattooRemoteImpl) bind TattooRemote::class
    singleOf(::TikkiRemoteImpl) bind TikkiRemote::class
    singleOf(::IndoRemoteImpl) bind IndoRemote::class
    singleOf(::FootRemoteImpl) bind FootRemote::class
}