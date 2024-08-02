package com.thezayin.categories.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.entities.PakistaniModel

@Dao
interface PakistaniDao {
    @Query("SELECT * FROM pakistani_table ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getImages(limit: Int, offset: Int): List<PakistaniModel>

    @Query("DELETE FROM pakistani_table")
    suspend fun deleteImages()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addImages(images: List<PakistaniModel>): List<Long>
}