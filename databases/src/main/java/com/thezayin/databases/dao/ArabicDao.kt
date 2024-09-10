package com.thezayin.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.databases.models.ArabicModel

@Dao
interface ArabicDao {
    @Query("SELECT * FROM arabic_table ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getImages(limit: Int, offset: Int): List<ArabicModel>

    @Query("DELETE FROM arabic_table")
    suspend fun deleteImages()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addImages(images: List<ArabicModel>): List<Long>
}