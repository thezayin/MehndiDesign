package com.thezayin.domain.model

data class CategoryModel(
    val id: Int,
    val icon: Int? = null,
    val title: String? = null,
    val isSelected: Boolean? = null,
)
