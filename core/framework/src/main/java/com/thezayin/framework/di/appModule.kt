package com.thezayin.framework.di

import com.thezayin.framework.config.RemoteConfig11
import com.thezayin.framework.remote.SupabaseApiClients
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::SupabaseApiClients)
    singleOf(::RemoteConfig11)
    single { Json { ignoreUnknownKeys = true } }
}