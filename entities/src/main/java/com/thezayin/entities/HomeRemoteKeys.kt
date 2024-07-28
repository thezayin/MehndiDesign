package com.thezayin.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_remote_keys_table")
data class HomeRemoteKeys (
    @PrimaryKey(autoGenerate = false)
    val _id: Int? = 0,

    val id: String? = "",
    val prevPage: Int? = 0,
    val nextPage: Int? = 0
)