package com.thezayin.framework.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(val id: Int, val name: String, val imageurl: String)