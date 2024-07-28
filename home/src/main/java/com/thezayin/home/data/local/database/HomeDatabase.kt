package com.thezayin.home.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thezayin.entities.HomeImages
import com.thezayin.entities.HomeRemoteKeys
import com.thezayin.home.data.local.dao.HomeImageDao
import com.thezayin.home.data.local.dao.HomeRemoteKeysDao

@Database(
    entities = [HomeImages::class, HomeRemoteKeys::class],
    version = 5,
    exportSchema = false
)
abstract class HomeDatabase : RoomDatabase() {
    abstract fun homeImageDao(): HomeImageDao
    abstract fun homeRemoteKeysDao(): HomeRemoteKeysDao
}