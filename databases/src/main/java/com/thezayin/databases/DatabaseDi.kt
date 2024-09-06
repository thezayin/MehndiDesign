package com.thezayin.databases

import android.content.Context
import androidx.room.Room

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, "mehndi_database")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun providePakistaniDao(database: AppDatabase) = database.pakistaniDao()
fun provideMoroccanDao(database: AppDatabase) = database.moroccanDao()
fun provideClassicDao(database: AppDatabase) = database.classicDao()
fun provideImageDao(database: AppDatabase) = database.homeImageDao()
fun provideTattooDao(database: AppDatabase) = database.tattooDao()
fun provideArabicDao(database: AppDatabase) = database.arabicDao()
fun provideFingerDao(database: AppDatabase) = database.fingerDao()
fun provideIndianDao(database: AppDatabase) = database.indianDao()
fun provideBridalDao(database: AppDatabase) = database.bridalDao()
fun provideTikkiDao(database: AppDatabase) = database.tikkiDao()
fun provideFootDao(database: AppDatabase) = database.footDao()
fun provideIndoDao(database: AppDatabase) = database.indoDao()
fun provideFavouriteDao(database: AppDatabase) = database.favouriteDao()

