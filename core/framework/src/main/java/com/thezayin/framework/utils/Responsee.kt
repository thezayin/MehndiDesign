package com.thezayin.framework.utils

sealed class Responsee<out T> {
    data object Loading : Responsee<Nothing>()
    data class Success<out T>(
        val data: T
    ) : Responsee<T>()

    data class Error(
        val e: String
    ) : Responsee<Nothing>()
}