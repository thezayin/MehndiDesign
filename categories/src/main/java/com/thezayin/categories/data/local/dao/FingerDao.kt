package com.thezayin.categories.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.entities.FingerModel

@Dao
interface FingerDao {
    @Query("SELECT * FROM finger_table ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getImages(limit: Int, offset: Int): List<FingerModel>

    @Query("DELETE FROM finger_table")
    suspend fun deleteImages()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addImages(images: List<FingerModel>): List<Long>
}