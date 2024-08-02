package com.thezayin.common.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

class SupabaseApiClient {
    fun sup(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://hnwqfbtmvitqjbcfndks.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imhud3FmYnRtdml0cWpiY2ZuZGtzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjE1MDk1NTcsImV4cCI6MjAzNzA4NTU1N30.qIpsNpX3bTtdQURmi6RbeiLEyUnrJb7ZZu1M2j_yBNE"
        ) {
            install(Postgrest)
        }
    }
}