package com.thezayin.like.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.entities.LikeImageModel

@Dao
interface LikeDao {
    @Query("SELECT * FROM liked_table")
    fun getAllImages(): List<LikeImageModel>

    @Query("DELETE FROM liked_table Where id = :id")
    suspend fun deleteImage(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImages(url: LikeImageModel): Long
}