package com.thezayin.framework.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.thezayin.framework.admob.data.repository.AppOpenAdManagerImpl
import com.thezayin.framework.admob.data.repository.InterstitialAdManagerImpl
import com.thezayin.framework.admob.data.repository.RewardedAdManagerImpl
import com.thezayin.framework.admob.domain.repository.AppOpenAdManager
import com.thezayin.framework.admob.domain.repository.InterstitialAdManager
import com.thezayin.framework.admob.domain.repository.RewardedAdManager
import com.thezayin.framework.analytics.analytics.Analytics
import com.thezayin.framework.analytics.analytics.AnalyticsImpl
import com.thezayin.framework.config.RemoteConfig
import com.thezayin.framework.pref.PrefManager
import com.thezayin.framework.remote.SupabaseApiClient
import com.thezayin.framework.session.category.CategorySession
import com.thezayin.framework.session.category.CategorySessionImpl
import com.thezayin.framework.session.image.ImageSession
import com.thezayin.framework.session.image.ImageSessionImpl
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val frameworkModule = module {
    singleOf(::PrefManager)
    singleOf(::RemoteConfig)
    singleOf(::SupabaseApiClient)
    factoryOf(::AnalyticsImpl) bind Analytics::class
    singleOf(::ImageSessionImpl) bind ImageSession::class
    singleOf(::CategorySessionImpl) bind CategorySession::class
    singleOf(::AppOpenAdManagerImpl) bind AppOpenAdManager::class
    singleOf(::RewardedAdManagerImpl) bind RewardedAdManager::class
    singleOf(::InterstitialAdManagerImpl) bind InterstitialAdManager::class
    single { Json { ignoreUnknownKeys = true } }
    single { FirebaseAnalytics.getInstance(get()) }
}