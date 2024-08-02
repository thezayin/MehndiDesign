package com.thezayin.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.thezayin.ads.GoogleManager
import com.thezayin.ads.ump.ConsentManager
import com.thezayin.analytics.helpers.AnalyticsHelper
import com.thezayin.analytics.helpers.FirebaseAnalyticsHelper
import com.thezayin.analytics.helpers.StubAnalyticsHelper
import com.thezayin.categories.data.local.repository.GetCategoryImagesImpl
import com.thezayin.categories.data.remote.CategoryRepositoryImpl
import com.thezayin.categories.data.remote.GetRemoteCategoriesImpl
import com.thezayin.categories.data.remote.HomeCategoryRepositoryImpl
import com.thezayin.categories.domain.repository.CategoryRepository
import com.thezayin.categories.domain.repository.GetCategoryImages
import com.thezayin.categories.domain.repository.GetRemoteCategories
import com.thezayin.categories.domain.repository.HomeCategoryRepository
import com.thezayin.categories.domain.usecase.local.ArabicImages
import com.thezayin.categories.domain.usecase.local.ArabicImagesImpl
import com.thezayin.categories.domain.usecase.local.BridalImages
import com.thezayin.categories.domain.usecase.local.BridalImagesImpl
import com.thezayin.categories.domain.usecase.local.ClassicImages
import com.thezayin.categories.domain.usecase.local.ClassicImagesImpl
import com.thezayin.categories.domain.usecase.local.FingerImages
import com.thezayin.categories.domain.usecase.local.FingerImagesImpl
import com.thezayin.categories.domain.usecase.local.FootImages
import com.thezayin.categories.domain.usecase.local.FootImagesImpl
import com.thezayin.categories.domain.usecase.local.IndianImages
import com.thezayin.categories.domain.usecase.local.IndianImagesImpl
import com.thezayin.categories.domain.usecase.local.IndoImages
import com.thezayin.categories.domain.usecase.local.IndoImagesImpl
import com.thezayin.categories.domain.usecase.local.MoroccanImages
import com.thezayin.categories.domain.usecase.local.MoroccanImagesImpl
import com.thezayin.categories.domain.usecase.local.PakistaniImages
import com.thezayin.categories.domain.usecase.local.PakistaniImagesImpl
import com.thezayin.categories.domain.usecase.local.TattooImages
import com.thezayin.categories.domain.usecase.local.TattooImagesImpl
import com.thezayin.categories.domain.usecase.local.TikkiImages
import com.thezayin.categories.domain.usecase.local.TikkiImagesImpl
import com.thezayin.categories.domain.usecase.remote.ArabicRemote
import com.thezayin.categories.domain.usecase.remote.ArabicRemoteImpl
import com.thezayin.categories.domain.usecase.remote.BridalRemote
import com.thezayin.categories.domain.usecase.remote.BridalRemoteImpl
import com.thezayin.categories.domain.usecase.remote.ClassicRemote
import com.thezayin.categories.domain.usecase.remote.ClassicRemoteImpl
import com.thezayin.categories.domain.usecase.remote.FingerRemote
import com.thezayin.categories.domain.usecase.remote.FingerRemoteImpl
import com.thezayin.categories.domain.usecase.remote.FootRemote
import com.thezayin.categories.domain.usecase.remote.FootRemoteImpl
import com.thezayin.categories.domain.usecase.remote.GetCategories
import com.thezayin.categories.domain.usecase.remote.GetCategoriesImpl
import com.thezayin.categories.domain.usecase.remote.HomeCategories
import com.thezayin.categories.domain.usecase.remote.HomeCategoriesImpl
import com.thezayin.categories.domain.usecase.remote.IndianRemote
import com.thezayin.categories.domain.usecase.remote.IndianRemoteImpl
import com.thezayin.categories.domain.usecase.remote.IndoRemote
import com.thezayin.categories.domain.usecase.remote.IndoRemoteImpl
import com.thezayin.categories.domain.usecase.remote.MoroccanRemote
import com.thezayin.categories.domain.usecase.remote.MoroccanRemoteImpl
import com.thezayin.categories.domain.usecase.remote.PakistaniRemote
import com.thezayin.categories.domain.usecase.remote.PakistaniRemoteImpl
import com.thezayin.categories.domain.usecase.remote.TattooRemote
import com.thezayin.categories.domain.usecase.remote.TattooRemoteImpl
import com.thezayin.categories.domain.usecase.remote.TikkiRemote
import com.thezayin.categories.domain.usecase.remote.TikkiRemoteImpl
import com.thezayin.categories.presentation.CategoriesViewModel
import com.thezayin.common.remote.SupabaseApiClient
import com.thezayin.common.viewmodel.MainViewModel
import com.thezayin.databases.provideArabicDao
import com.thezayin.databases.provideBridalDao
import com.thezayin.databases.provideClassicDao
import com.thezayin.databases.provideDatabase
import com.thezayin.databases.provideFingerDao
import com.thezayin.databases.provideFootDao
import com.thezayin.databases.provideImageDao
import com.thezayin.databases.provideIndianDao
import com.thezayin.databases.provideIndoDao
import com.thezayin.databases.provideLikeDao
import com.thezayin.databases.provideMoroccanDao
import com.thezayin.databases.providePakistaniDao
import com.thezayin.databases.provideTattooDao
import com.thezayin.databases.provideTikkiDao
import com.thezayin.home.data.local.repository.ImageRepositoryImpl
import com.thezayin.home.data.repository.RemoteRepositoryImpl
import com.thezayin.home.domain.repository.ImageRepository
import com.thezayin.home.domain.repository.RemoteRepository
import com.thezayin.home.domain.usecase.FetchRemote
import com.thezayin.home.domain.usecase.FetchRemoteImpl
import com.thezayin.home.domain.usecase.GetHomeImages
import com.thezayin.home.domain.usecase.GetHomeImagesImpl
import com.thezayin.home.presentation.HomeViewModel
import com.thezayin.like.data.repository.LikeMenuRepositoryImpl
import com.thezayin.like.data.repository.LikeRepositoryImpl
import com.thezayin.like.domain.repository.LikeMenuRepository
import com.thezayin.like.domain.repository.LikeRepository
import com.thezayin.like.domain.usecase.DeleteImage
import com.thezayin.like.domain.usecase.DeleteImageImpl
import com.thezayin.like.domain.usecase.InsertImage
import com.thezayin.like.domain.usecase.InsertImageImpl
import com.thezayin.like.domain.usecase.LikeMenuList
import com.thezayin.like.domain.usecase.LikeMenuListImpl
import com.thezayin.like.domain.usecase.LikedImages
import com.thezayin.like.domain.usecase.LikedImagesImpl
import com.thezayin.like.presentation.LikeViewModel
import com.thezayin.preview.data.PreviewMenuRepositoryImpl
import com.thezayin.preview.data.SaveImageRepositoryImpl
import com.thezayin.preview.domain.repository.PreviewMenuRepository
import com.thezayin.preview.domain.repository.SaveImageRepository
import com.thezayin.preview.domain.usecase.PreviewMenuItems
import com.thezayin.preview.domain.usecase.PreviewMenuItemsImpl
import com.thezayin.preview.domain.usecase.SaveImage
import com.thezayin.preview.domain.usecase.SaveImageImpl
import com.thezayin.preview.presentation.PreviewViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val adModule = module {
    viewModelOf(::MainViewModel)
    single { ConsentManager(androidContext()) }
    single { GoogleManager(androidContext(), get()) }
}

val categoryModule = module {
    viewModel {
        CategoriesViewModel(
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
            get()
        )
    }

    singleOf(::HomeCategoryRepositoryImpl) bind HomeCategoryRepository::class
    singleOf(::GetRemoteCategoriesImpl) bind GetRemoteCategories::class
    singleOf(::CategoryRepositoryImpl) bind CategoryRepository::class
    singleOf(::GetCategoryImagesImpl) bind GetCategoryImages::class
    singleOf(::HomeCategoriesImpl) bind HomeCategories::class
    singleOf(::GetCategoriesImpl) bind GetCategories::class

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

val homeModule = module {
    singleOf(::RemoteRepositoryImpl) bind RemoteRepository::class
    singleOf(::ImageRepositoryImpl) bind ImageRepository::class
    singleOf(::GetHomeImagesImpl) bind GetHomeImages::class
    singleOf(::FetchRemoteImpl) bind FetchRemote::class
    singleOf(::SupabaseApiClient)
    viewModelOf(::HomeViewModel)
}

val previewModule = module {
    viewModelOf(::PreviewViewModel)
    singleOf(::SaveImageImpl) bind SaveImage::class
    singleOf(::PreviewMenuItemsImpl) bind PreviewMenuItems::class
    singleOf(::SaveImageRepositoryImpl) bind SaveImageRepository::class
    singleOf(::PreviewMenuRepositoryImpl) bind PreviewMenuRepository::class
}

val likeModule = module {
    singleOf(::LikeMenuRepositoryImpl) bind LikeMenuRepository::class
    singleOf(::LikeRepositoryImpl) bind LikeRepository::class
    singleOf(::LikeMenuListImpl) bind LikeMenuList::class
    singleOf(::DeleteImageImpl) bind DeleteImage::class
    singleOf(::LikedImagesImpl) bind LikedImages::class
    singleOf(::InsertImageImpl) bind InsertImage::class
    viewModelOf(::LikeViewModel)
}

val analyticsHelperModule = module {
    single { FirebaseAnalyticsHelper(get()) }
    single { FirebaseAnalytics.getInstance(androidContext()) }
    factoryOf(::StubAnalyticsHelper) bind AnalyticsHelper::class
    factoryOf(::FirebaseAnalyticsHelper) bind AnalyticsHelper::class
}

val databaseModule = module {
    single { provideLikeDao(get()) }
    single { provideFootDao(get()) }
    single { provideIndoDao(get()) }
    single { provideTikkiDao(get()) }
    single { provideImageDao(get()) }
    single { provideTattooDao(get()) }
    single { provideFingerDao(get()) }
    single { provideArabicDao(get()) }
    single { provideBridalDao(get()) }
    single { provideIndianDao(get()) }
    single { provideClassicDao(get()) }
    single { provideMoroccanDao(get()) }
    single { providePakistaniDao(get()) }
    single { provideDatabase(androidContext()) }
}

val appModule = module {

}

