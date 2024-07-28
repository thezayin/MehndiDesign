package com.thezayin.databases

import android.content.Context
import androidx.room.Room
import com.thezayin.home.data.local.database.HomeDatabase

fun provideDatabase(context: Context) =
    Room.databaseBuilder(context, HomeDatabase::class.java, "unsplash_database")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

fun provideImageDao(database: HomeDatabase) = database.homeImageDao()
fun provideRemoteKeysDao(database: HomeDatabase) = database.homeRemoteKeysDao()