package com.thezayin.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.ads.GoogleManager
import com.thezayin.domain.model.CategoryModel
import com.thezayin.domain.usecase.GetCategories
import com.thezayin.framework.config.RemoteConfig
import com.thezayin.framework.utils.Response
import com.thezayin.presentation.event.CategoryEvent
import com.thezayin.presentation.state.CategoryState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel(
    val googleManager: GoogleManager,
    val remoteConfig: RemoteConfig,
    private val getCategories: GetCategories,
) : ViewModel() {

    private val _categoryListState = MutableStateFlow(CategoryState.CategoryListUiState())
    val categoryListState = _categoryListState.asStateFlow()


    var nativeAd = mutableStateOf<NativeAd?>(null)
        private set

    init {
        fetchCategories()
    }

    fun getNativeAd() = viewModelScope.launch {
        nativeAd.value = googleManager.createNativeAd().apply {} ?: run {
            delay(3000)
            googleManager.createNativeAd()
        }
    }

    private fun categoryEvent(event: CategoryEvent) {
        when (event) {
            is CategoryEvent.CategoriesList -> {
                _categoryListState.update {
                    it.copy(categories = event.result)
                }
            }

            is CategoryEvent.ErrorMessage -> {
                _categoryListState.update {
                    it.copy(errorMessage = event.errorMessage)
                }
            }

            CategoryEvent.HideErrorDialog -> {
                _categoryListState.update {
                    it.copy(showError = false)
                }
            }

            CategoryEvent.HideLoading -> {
                _categoryListState.update {
                    it.copy(isLoading = false)
                }
            }

            CategoryEvent.ShowError -> {
                _categoryListState.update {
                    it.copy(showError = true)
                }
            }

            CategoryEvent.ShowLoading -> {
                _categoryListState.update {
                    it.copy(isLoading = true)
                }
            }
        }
    }


    private fun fetchCategories() = viewModelScope.launch {
        getCategories().collect { response ->
            when (response) {
                is Response.Success -> {
                    categoriesList(response.data)
                    hideLoading()
                }

                is Response.Loading -> {
                    showLoading()
                    hideErrorDialog()
                }

                is Response.Error -> {
                    hideLoading()
                    showError()
                    errorMessages(response.e)
                }
            }
        }
    }

    private fun categoriesList(categories: List<CategoryModel>) {
        categoryEvent(CategoryEvent.CategoriesList(categories))
    }

    private fun errorMessages(error: String) {
        categoryEvent(CategoryEvent.ErrorMessage(error))
    }

    private fun showError() {
        categoryEvent(CategoryEvent.ShowError)
    }

    fun hideErrorDialog() {
        categoryEvent(CategoryEvent.HideErrorDialog)
    }

    private fun hideLoading() {
        categoryEvent(CategoryEvent.HideLoading)
    }

    private fun showLoading() {
        categoryEvent(CategoryEvent.ShowLoading)
    }
}