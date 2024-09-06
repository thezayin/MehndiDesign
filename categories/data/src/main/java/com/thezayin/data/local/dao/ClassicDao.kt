package com.thezayin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.entities.ClassicModel

@Dao
interface ClassicDao {
    @Query("SELECT * FROM classic_table ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getImages(limit: Int, offset: Int): List<ClassicModel>

    @Query("DELETE FROM classic_table")
    suspend fun deleteImages()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addImages(images: List<ClassicModel>): List<Long>
}