package com.thezayin.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "moroccan_table")
@kotlinx.serialization.Serializable
data class MoroccanModel (
    @PrimaryKey(autoGenerate = false)
    @SerialName("id")
    val id: Int = 0,
    @SerialName("URL")
    val URL: String = "",
)