package com.thezayin.home.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.entities.HomeRemoteKeys

@Dao
interface HomeRemoteKeysDao {
    @Query("SELECT * FROM home_remote_keys_table WHERE id = :id")
    suspend fun getRemoteKeys(id: String): HomeRemoteKeys

    @Query("DELETE FROM home_remote_keys_table")
    suspend fun deleteAllRemoteKeys()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<HomeRemoteKeys>)
}