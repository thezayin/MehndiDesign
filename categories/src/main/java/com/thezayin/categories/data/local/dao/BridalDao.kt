package com.thezayin.categories.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.entities.BridalModel

@Dao
interface BridalDao {
    @Query("SELECT * FROM bridal_table ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getImages(limit: Int, offset: Int): List<BridalModel>

    @Query("DELETE FROM bridal_table")
    suspend fun deleteImages()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addImages(images: List<BridalModel>): List<Long>
}