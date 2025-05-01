package com.thezayin.framework.model

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val id: Int,
    val image_url: String,
    val category_id: Int
)