package com.thezayin.mehndidesign.nav

import kotlinx.serialization.Serializable

@Serializable
object SplashScreenNav

@Serializable
object HomeScreenNav

@Serializable
object CategoryListScreenNav

@Serializable
object FavouriteScreenNav

@Serializable
object SettingScreenNav

@Serializable
data class CategoryScreenNav(
    val id: Int? = null,
    val title: String? = null
)

@Serializable
data class PreviewScreenNav(
    val url: String? = null,
    val id: Int? = null,
    val isFavourite: Boolean? = null
)
