package com.thezayin.home.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.entities.HomeImages

@Dao
interface HomeImageDao {
    @Query("SELECT * FROM home_images_table")
    fun getAllImages(): PagingSource<Int, HomeImages>

    @Query("DELETE FROM home_images_table")
    suspend fun deleteAllImages()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<HomeImages>)
}