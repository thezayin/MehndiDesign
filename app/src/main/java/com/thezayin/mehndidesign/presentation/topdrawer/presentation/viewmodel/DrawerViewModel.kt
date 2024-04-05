package com.thezayin.mehndidesign.presentation.topdrawer.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.thezayin.mehndidesign.domain.model.UnsplashImage
import com.thezayin.mehndidesign.domain.usecase.SearchUseCase
import com.thezayin.mehndidesign.presentation.topdrawer.domain.model.DrawerItem
import com.thezayin.mehndidesign.presentation.topdrawer.domain.usecase.DrawerUseCase
import com.thezayin.mehndidesign.presentation.utils.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DrawerViewModel(
    private val drawerUseCase: DrawerUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    var getHomeState by mutableStateOf(HomeIconState())
        private set

    var isLoadingState by mutableStateOf(GetLoadingState())
        private set

    init {
        getIconList()
    }

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedImages = MutableStateFlow<PagingData<UnsplashImage>>(PagingData.empty())
    val searchedImages = _searchedImages

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchQuery(query: String) {
        viewModelScope.launch {
            searchUseCase(query).cachedIn(viewModelScope).collect {
                _searchedImages.value = it
            }
        }
    }

    private fun getIconList() = viewModelScope.launch {
        drawerUseCase().collect {
            when (it) {
                is Response.Success -> {
                    getHomeState = getHomeState.copy(list = it.data)
                    delay(250L)
                    isLoadingState.isLoading.value = false
                }

                is Response.Loading -> {
                    isLoadingState.isLoading.value = true
                }

                is Response.Error -> {
                    isLoadingState.isLoading.value = false
                }
            }
        }
    }

    data class HomeIconState(
        val list: List<DrawerItem> = emptyList()
    )

    data class GetLoadingState(
        val isLoading: MutableState<Boolean> = mutableStateOf(false)
    )

    class ServiceDataState {
        var service = mutableStateListOf<DrawerItem>()
        fun itemSelected(serviceOptions: DrawerItem) {
            val iterator = service.listIterator()
            while (iterator.hasNext()) {
                val item = iterator.next()
                iterator.set(
                    if (item.id == serviceOptions.id) {
                        serviceOptions
                    } else {
                        item.copy(
                            isSelected = false
                        )
                    }
                )
            }
        }

        fun setNewList(serviceOptions: List<DrawerItem>) {
            this.service = serviceOptions.toMutableStateList()
        }
    }
}