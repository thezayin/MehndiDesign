package com.thezayin.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "indian_table")
@kotlinx.serialization.Serializable
data class IndianModel (
    @PrimaryKey(autoGenerate = false)
    @SerialName("id")
    val id: Int = 0,
    @SerialName("URL")
    val URL: String = "",
)