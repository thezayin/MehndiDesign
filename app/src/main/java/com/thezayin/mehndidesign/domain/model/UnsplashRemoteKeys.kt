package com.thezayin.mehndidesign.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thezayin.mehndidesign.presentation.utils.Constants.UNSPLASH_REMOTE_KEY_TABLE

@Entity(tableName = UNSPLASH_REMOTE_KEY_TABLE)
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val _id: Int?,

    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)