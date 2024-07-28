package com.thezayin.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "home_images_table")
@kotlinx.serialization.Serializable
data class HomeImages(
    @PrimaryKey(autoGenerate = true)
    @SerialName("image_id")
    val image_id: Int? = null,

    @SerialName("id")
    val id: Int = 0,
    @SerialName("URL")
    val URL: String = "",
)