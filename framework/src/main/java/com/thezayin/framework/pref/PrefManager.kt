package com.thezayin.framework.pref

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.thezayin.framework.model.Category
import com.thezayin.framework.model.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PrefManager(context: Context) {

    companion object {
        private const val KEY_IS_FIRST_TIME = "is_first_time"
        private const val KEY_SELECTED_LANGUAGE = "selected_language"
    }

    private val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    private val _isFirstTime = MutableStateFlow(true)
    val isFirstTime: StateFlow<Boolean> = _isFirstTime

    init {
        _isFirstTime.value = sharedPreferences.getBoolean(KEY_IS_FIRST_TIME, true)
    }

    /**
     * Marks that the user has completed onboarding.
     */
    fun setOnboardingCompleted() {
        sharedPreferences.edit {
            putBoolean(KEY_IS_FIRST_TIME, false)
        }
        _isFirstTime.value = false
    }

    // Save Categories to SharedPreferences
    fun saveCategories(categories: List<Category>) {
        val json = gson.toJson(categories)
        sharedPreferences.edit { putString("categories", json) }
    }

    // Save Images to SharedPreferences
    fun saveImages(images: List<Image>) {
        val json = gson.toJson(images)
        sharedPreferences.edit { putString("images", json) }
    }

    // Get Categories from SharedPreferences
    fun getCategories(): List<Category> {
        val json = sharedPreferences.getString("categories", null)
        return if (json != null) {
            gson.fromJson(json, Array<Category>::class.java).toList()
        } else {
            emptyList()
        }
    }

    // Get Images from SharedPreferences
    fun getImages(): List<Image> {
        val json = sharedPreferences.getString("images", null)
        return if (json != null) {
            gson.fromJson(json, Array<Image>::class.java).toList()
        } else {
            emptyList()
        }
    }
}
