package com.thezayin.framework.remote

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.thezayin.framework.model.Category
import com.thezayin.framework.model.Image
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import timber.log.Timber

class SupabaseApiClient {
    private val supabaseClient: SupabaseClient = createSupabaseClient(
        supabaseUrl = "https://hnwqfbtmvitqjbcfndks.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imhud3FmYnRtdml0cWpiY2ZuZGtzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjE1MDk1NTcsImV4cCI6MjAzNzA4NTU1N30.qIpsNpX3bTtdQURmi6RbeiLEyUnrJb7ZZu1M2j_yBNE"
    ) {
        install(Postgrest)
    }

    suspend fun fetchCategories(): List<Category> {
        try {
            val response = supabaseClient.postgrest.from("categories")
                .select()
                .decodeList<Category>()

            if (response.isEmpty()) {
                Timber.tag("SupabaseApiClient").d("No categories found")
                FirebaseCrashlytics.getInstance().log("Empty categories response")
            } else {
                Timber.tag("SupabaseApiClient").d("Fetched categories: ${response.size}")
            }
            return response
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
            Timber.tag("SupabaseApiClient").e("Error fetching categories: ${e.message}")
            return emptyList()
        }
    }

    suspend fun fetchImages(): List<Image> {
        try {
            val response = supabaseClient.postgrest.from("images")
                .select()
                .decodeList<Image>()
            if (response.isEmpty()) {
                Timber.tag("SupabaseApiClient").d("No Images found")
                FirebaseCrashlytics.getInstance().log("Empty Images response")
            } else {
                Timber.tag("SupabaseApiClient").d("Fetched Images: ${response.size}")
            }
            return response
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().recordException(e)
            Timber.tag("SupabaseApiClient").e("Error fetching images: ${e.message}")
            return emptyList()
        }
    }
}