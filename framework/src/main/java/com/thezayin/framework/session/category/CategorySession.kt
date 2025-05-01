package com.thezayin.framework.session.category

import com.thezayin.framework.model.Category

/**
 * Remembers which category the user tapped.
 */
interface CategorySession {
    /** Call when user picks a category. */
    fun select(category: Category)
    /** The last‐selected category, or null. */
    fun getSelected(): Category?
}