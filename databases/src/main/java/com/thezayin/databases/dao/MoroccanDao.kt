package com.thezayin.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.databases.models.MoroccanModel

@Dao
interface MoroccanDao {
    @Query("SELECT * FROM moroccan_table ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getImages(limit: Int, offset: Int): List<MoroccanModel>

    @Query("DELETE FROM moroccan_table")
    suspend fun deleteImages()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addImages(images: List<MoroccanModel>): List<Long>
}