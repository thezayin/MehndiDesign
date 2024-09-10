package com.thezayin.databases.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "indo_table")
@kotlinx.serialization.Serializable
data class IndoModel(
    @PrimaryKey(autoGenerate = false)
    @SerialName("id")
    val id: Int = 0,
    @SerialName("URL")
    val URL: String = "",
)