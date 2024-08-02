package com.thezayin.home.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.entities.ImageModel

@Dao
interface HomeImageDao {
    @Query("SELECT * FROM home_images_table ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getAllImages(limit: Int, offset: Int): List<ImageModel>

    @Query("DELETE FROM home_images_table")
    suspend fun deleteAllImages()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImages(images: List<ImageModel>):List<Long>
}