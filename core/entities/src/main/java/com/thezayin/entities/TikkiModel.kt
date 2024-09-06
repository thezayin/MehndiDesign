package com.thezayin.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "tikki_table")
@kotlinx.serialization.Serializable
data class TikkiModel(
    @PrimaryKey(autoGenerate = false)
    @SerialName("id")
    val id: Int = 0,
    @SerialName("URL")
    val URL: String = "",
)