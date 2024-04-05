package com.thezayin.mehndidesign.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thezayin.mehndidesign.data.local.dao.UnsplashImageDao
import com.thezayin.mehndidesign.data.local.dao.UnsplashRemoteKeysDao
import com.thezayin.mehndidesign.domain.model.UnsplashImage
import com.thezayin.mehndidesign.domain.model.UnsplashRemoteKeys

@Database(
    entities = [UnsplashImage::class, UnsplashRemoteKeys::class],
    version = 4,
    exportSchema = false
)
abstract class UnsplashDatabase : RoomDatabase() {
    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao
}