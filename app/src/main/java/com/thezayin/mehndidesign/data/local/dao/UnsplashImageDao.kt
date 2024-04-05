package com.thezayin.mehndidesign.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thezayin.mehndidesign.domain.model.UnsplashImage

@Dao
interface UnsplashImageDao {
    @Query("SELECT * FROM unsplash_image_table")
     fun getAllImages(): PagingSource<Int, UnsplashImage>

    @Query("DELETE FROM unsplash_image_table")
    suspend fun deleteAllImages()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<UnsplashImage>)
}