package com.thezayin.framework.di

import com.thezayin.framework.remote.RemoteConfig
import com.thezayin.framework.remote.SupabaseApiClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::SupabaseApiClient)
    singleOf(::RemoteConfig)
}