package com.thezayin.mehndidesign.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Urls(
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)