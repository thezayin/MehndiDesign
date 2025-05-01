package com.thezayin.upload.domain.model

data class ImageModel(
    val id: Int, // Assume auto-increment in DB
    val imageUrl: String,
    val category: String // Name of the table/category
    // Add other relevant fields if necessary
)