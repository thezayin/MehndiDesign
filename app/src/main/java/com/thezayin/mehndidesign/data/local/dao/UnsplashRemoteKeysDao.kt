package com.thezayin.mehndidesign.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.mehndidesign.domain.model.UnsplashRemoteKeys

@Dao
interface UnsplashRemoteKeysDao {
    @Query("SELECT * FROM unsplash_remote_keys_table WHERE id = :id")
    suspend fun getRemoteKeys(id: String): UnsplashRemoteKeys

    @Query("DELETE FROM unsplash_remote_keys_table")
    suspend fun deleteAllRemoteKeys()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<UnsplashRemoteKeys>)
}