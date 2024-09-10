package com.thezayin.databases.di

import com.thezayin.databases.db.provideArabicDao
import com.thezayin.databases.db.provideBridalDao
import com.thezayin.databases.db.provideClassicDao
import com.thezayin.databases.db.provideDatabase
import com.thezayin.databases.db.provideFavouriteDao
import com.thezayin.databases.db.provideFingerDao
import com.thezayin.databases.db.provideFootDao
import com.thezayin.databases.db.provideImageDao
import com.thezayin.databases.db.provideIndianDao
import com.thezayin.databases.db.provideIndoDao
import com.thezayin.databases.db.provideMoroccanDao
import com.thezayin.databases.db.providePakistaniDao
import com.thezayin.databases.db.provideTattooDao
import com.thezayin.databases.db.provideTikkiDao
import org.koin.dsl.module

val databaseModule = module {
    single { provideFavouriteDao(get()) }
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
    single { provideDatabase(get()) }
}