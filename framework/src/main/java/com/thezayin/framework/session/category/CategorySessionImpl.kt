package com.thezayin.framework.session.category

import com.thezayin.framework.model.Category

class CategorySessionImpl : CategorySession {
    private var selected: Category? = null
    override fun select(category: Category) { selected = category }
    override fun getSelected(): Category? = selected
}