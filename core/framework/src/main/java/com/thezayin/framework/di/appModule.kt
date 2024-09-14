package com.thezayin.framework.di

import com.thezayin.framework.config.RemoteConfig
import com.thezayin.framework.remote.SupabaseApiClient
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::SupabaseApiClient)
    singleOf(::RemoteConfig)
    single { Json { ignoreUnknownKeys = true } }
}