package com.thezayin.mehndidesign.presentation.search.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery
}