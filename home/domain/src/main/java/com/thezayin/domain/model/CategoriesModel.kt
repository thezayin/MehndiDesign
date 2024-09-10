package com.thezayin.domain.model

/**
 * Data class representing a category in the application.
 *
 * @param id Unique identifier for the category.
 * @param iconResId Optional resource ID for the category's icon.
 * @param name Optional title or name of the category.
 * @param isSelected Optional flag indicating whether the category is selected.
 */
data class CategoriesModel(
    val id: Int,
    val iconResId: Int? = null,
    val name: String? = null,
    val isSelected: Boolean? = null,
)