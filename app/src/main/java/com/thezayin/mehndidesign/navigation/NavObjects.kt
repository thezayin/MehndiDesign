package com.thezayin.mehndidesign.navigation

@kotlinx.serialization.Serializable
object SplashScreenNav

@kotlinx.serialization.Serializable
object HomeScreenNav

@kotlinx.serialization.Serializable
object CategoryScreenNav

@kotlinx.serialization.Serializable
object FavouriteScreenNav

@kotlinx.serialization.Serializable
object SettingScreenNav

@kotlinx.serialization.Serializable
data class CategoryImageScreenNav(
    val id: Int? = null,
    val title: String? = null
)

@kotlinx.serialization.Serializable
data class PreviewScreenNav(
    val url: String? = null,
    val id: Int? = null,
    val isFavourite: Boolean? = null
)
