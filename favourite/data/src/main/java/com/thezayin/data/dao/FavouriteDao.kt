package com.thezayin.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.entities.LikeImageModel

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM liked_table")
    fun getAllImages(): List<LikeImageModel>

    @Query("DELETE FROM liked_table Where id = :id")
    suspend fun deleteImage(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImages(url: LikeImageModel): Long

    @Query("SELECT EXISTS(SELECT * FROM liked_table WHERE url = :url)")
    fun checkImage(url: String): Boolean
}