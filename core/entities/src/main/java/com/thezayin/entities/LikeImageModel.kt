package com.thezayin.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_table")
@kotlinx.serialization.Serializable
data class LikeImageModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val URL: String,
)