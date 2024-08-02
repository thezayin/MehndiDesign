package com.thezayin.categories.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.entities.IndianModel

@Dao
interface IndianDao {
    @Query("SELECT * FROM indian_table ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getImages(limit: Int, offset: Int): List<IndianModel>

    @Query("DELETE FROM indian_table")
    suspend fun deleteImages()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addImages(images: List<IndianModel>): List<Long>
}