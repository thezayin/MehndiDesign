package com.thezayin.mehndidesign.data.di

import android.content.Context
import androidx.room.Room
import com.thezayin.mehndidesign.data.local.database.UnsplashDatabase

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, UnsplashDatabase::class.java, "unsplash_database")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun provideUnsplashImageDao(database: UnsplashDatabase) = database.unsplashImageDao()
fun provideUnsplashRemoteKeysDao(database: UnsplashDatabase) = database.unsplashRemoteKeysDao()