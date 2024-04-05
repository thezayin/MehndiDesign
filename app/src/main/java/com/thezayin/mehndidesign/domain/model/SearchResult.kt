package com.thezayin.mehndidesign.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResult(
    @SerialName("results")
    val images: List<UnsplashImage>,
    @SerialName("total_pages")
    val pages: Int,
)