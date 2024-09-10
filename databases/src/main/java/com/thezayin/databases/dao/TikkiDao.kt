package com.thezayin.databases.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.databases.models.TikkiModel

@Dao
interface TikkiDao {
    @Query("SELECT * FROM tikki_table ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getImages(limit: Int, offset: Int): List<TikkiModel>

    @Query("DELETE FROM tikki_table")
    suspend fun deleteImages()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addImages(images: List<TikkiModel>): List<Long>
}