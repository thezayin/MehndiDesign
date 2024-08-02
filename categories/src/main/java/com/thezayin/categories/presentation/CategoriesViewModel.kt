package com.thezayin.categories.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.thezayin.categories.domain.usecase.local.ArabicImages
import com.thezayin.categories.domain.usecase.local.BridalImages
import com.thezayin.categories.domain.usecase.local.ClassicImages
import com.thezayin.categories.domain.usecase.local.FingerImages
import com.thezayin.categories.domain.usecase.local.FootImages
import com.thezayin.categories.domain.usecase.local.IndianImages
import com.thezayin.categories.domain.usecase.local.IndoImages
import com.thezayin.categories.domain.usecase.local.MoroccanImages
import com.thezayin.categories.domain.usecase.local.PakistaniImages
import com.thezayin.categories.domain.usecase.local.TattooImages
import com.thezayin.categories.domain.usecase.local.TikkiImages
import com.thezayin.categories.domain.usecase.remote.ArabicRemote
import com.thezayin.categories.domain.usecase.remote.BridalRemote
import com.thezayin.categories.domain.usecase.remote.ClassicRemote
import com.thezayin.categories.domain.usecase.remote.FingerRemote
import com.thezayin.categories.domain.usecase.remote.FootRemote
import com.thezayin.categories.domain.usecase.remote.GetCategories
import com.thezayin.categories.domain.usecase.remote.HomeCategories
import com.thezayin.categories.domain.usecase.remote.IndianRemote
import com.thezayin.categories.domain.usecase.remote.IndoRemote
import com.thezayin.categories.domain.usecase.remote.MoroccanRemote
import com.thezayin.categories.domain.usecase.remote.PakistaniRemote
import com.thezayin.categories.domain.usecase.remote.TattooRemote
import com.thezayin.categories.domain.usecase.remote.TikkiRemote
import com.thezayin.entities.GetCategoryState
import com.thezayin.entities.GetErrorState
import com.thezayin.entities.GetImagesState
import com.thezayin.entities.GetLoadingState
import com.thezayin.framework.utils.Response
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val getCategories: GetCategories,
    private val getHomeCategories: HomeCategories,
    private val arabic: ArabicImages,
    private val bridal: BridalImages,
    private val classic: ClassicImages,
    private val finger: FingerImages,
    private val foot: FootImages,
    private val indian: IndianImages,
    private val indo: IndoImages,
    private val moroccan: MoroccanImages,
    private val pakistani: PakistaniImages,
    private val tattoo: TattooImages,
    private val tikki: TikkiImages,

    private val arabicRemote: ArabicRemote,
    private val bridalRemote: BridalRemote,
    private val classicRemote: ClassicRemote,
    private val fingerRemote: FingerRemote,
    private val footRemote: FootRemote,
    private val indianRemote: IndianRemote,
    private val indoRemote: IndoRemote,
    private val moroccanRemote: MoroccanRemote,
    private val pakistaniRemote: PakistaniRemote,
    private val tattooRemote: TattooRemote,
    private val tikkiRemote: TikkiRemote
) : ViewModel() {

    private val _homeCategories = MutableStateFlow(GetCategoryState())
    val homeCategories = _homeCategories.asStateFlow()

    private val _categories = MutableStateFlow(GetCategoryState())
    val categories = _categories.asStateFlow()

    private val _imagesUrl = MutableStateFlow(GetImagesState())
    val imagesUrl = _imagesUrl.asStateFlow()

    private val _isQueryError = MutableStateFlow(GetErrorState())
    val isQueryError = _isQueryError.asStateFlow()

    private val _isLoading = MutableStateFlow(GetLoadingState())
    val isLoading = _isLoading.asStateFlow()

    init {
        fetchCategories()
        fetchHomeCategories()
    }

    fun fetchRemoteTattoo() = viewModelScope.launch {
        tattooRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchTattooImages()
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    fun fetchRemotePakistani() = viewModelScope.launch {
        pakistaniRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchPakistaniImages()
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }

        }
    }

    fun fetchRemoteMoroccan() = viewModelScope.launch {
        moroccanRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchMoroccanImages()
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    fun fetchRemoteIndo() = viewModelScope.launch {
        indoRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchIndoImages()
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    fun fetchRemoteFoot() = viewModelScope.launch {
        footRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchFootImages()
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    fun fetchRemoteIndian() = viewModelScope.launch {
        indianRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchIndianImages()
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    fun fetchRemoteFinger() = viewModelScope.launch {
        fingerRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchFingerImages()
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    fun fetchRemoteClassic() = viewModelScope.launch {
        classicRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchClassicImages()
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    fun fetchRemoteTikki() = viewModelScope.launch {
        tikkiRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchTikkiImages()
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    fun fetchRemoteBridal() = viewModelScope.launch {
        bridalRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchBridalImages()
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    fun fetchRemoteArabic() = viewModelScope.launch {
        arabicRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchArabicImages()
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }


    private fun fetchHomeCategories() = viewModelScope.launch {
        getHomeCategories().collect { response ->
            when (response) {
                is Response.Success -> {
                    _homeCategories.update { it.copy(list = response.data) }
                }

                else -> {
                    Log.d("CategoriesViewModel", "fetchHomeCategories: ${response}")
                }
            }
        }
    }

    private fun fetchCategories() = viewModelScope.launch {
        getCategories().collect { response ->
            when (response) {
                is Response.Success -> {
                    _categories.update { it.copy(list = response.data) }
                    delay(3000L)
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    private fun fetchTattooImages() = viewModelScope.launch {
        tattoo().collect { response ->
            when (response) {
                is Response.Success -> {
                    _imagesUrl.update { it ->
                        it.copy(list = response.data.map { response ->
                            response.map {
                                it.URL
                            }
                        })
                    }
                    delay(3000L)
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    private fun fetchPakistaniImages() = viewModelScope.launch {
        pakistani().collect { response ->
            when (response) {
                is Response.Success -> {
                    _imagesUrl.update { it ->
                        it.copy(list = response.data.map { res -> res.map { it.URL } })
                    }
                    delay(3000L)
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    private fun fetchMoroccanImages() = viewModelScope.launch {
        moroccan().collect { response ->
            when (response) {
                is Response.Success -> {
                    _imagesUrl.update { it ->
                        it.copy(list = response.data.map { response ->
                            response.map {
                                it.URL
                            }
                        })
                    }
                    delay(3000L)
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    private fun fetchIndoImages() = viewModelScope.launch {
        indo().collect { response ->
            when (response) {
                is Response.Success -> {
                    _imagesUrl.update { it ->
                        it.copy(list = response.data.map { response ->
                            response.map {
                                it.URL
                            }
                        })
                    }
                    delay(3000L)
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    private fun fetchFootImages() = viewModelScope.launch {
        foot().collect { response ->
            when (response) {
                is Response.Success -> {
                    _imagesUrl.update { it ->
                        it.copy(list = response.data.map { response ->
                            response.map {
                                it.URL
                            }
                        })
                    }
                    delay(3000L)
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    private fun fetchIndianImages() = viewModelScope.launch {
        indian().collect { response ->
            when (response) {
                is Response.Success -> {
                    _imagesUrl.update { it ->
                        it.copy(list = response.data.map { response ->
                            response.map {
                                it.URL
                            }
                        })
                    }
                    delay(3000L)
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    private fun fetchFingerImages() = viewModelScope.launch {
        finger().collect { response ->
            when (response) {
                is Response.Success -> {
                    _imagesUrl.update { it ->
                        it.copy(list = response.data.map { response ->
                            response.map {
                                it.URL
                            }
                        })
                    }
                    delay(3000L)
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    private fun fetchClassicImages() = viewModelScope.launch {
        classic().collect { response ->
            when (response) {
                is Response.Success -> {
                    _imagesUrl.update { it ->
                        it.copy(list = response.data.map { response ->
                            response.map {
                                it.URL
                            }
                        })
                    }
                    delay(3000L)
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    private fun fetchTikkiImages() = viewModelScope.launch {
        tikki().collect { response ->
            when (response) {
                is Response.Success -> {
                    _imagesUrl.update { it ->
                        it.copy(list = response.data.map { response ->
                            response.map {
                                it.URL
                            }
                        })
                    }
                    delay(3000L)
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    private fun fetchBridalImages() = viewModelScope.launch {
        bridal().collect { response ->
            when (response) {
                is Response.Success -> {
                    _imagesUrl.update { it ->
                        it.copy(list = response.data.map { response ->
                            response.map {
                                it.URL
                            }
                        })
                    }
                    delay(3000L)
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

    private fun fetchArabicImages() = viewModelScope.launch {
        arabic().collect { response ->
            when (response) {
                is Response.Success -> {
                    _imagesUrl.update { it ->
                        it.copy(list = response.data.map { response ->
                            response.map {
                                it.URL
                            }
                        })
                    }
                    delay(3000L)
                    _isLoading.update { it.copy(isLoading = false) }
                }

                is Response.Loading -> {
                    _isLoading.update { it.copy(isLoading = true) }
                }

                is Response.Error -> {
                    _isLoading.update { it.copy(isLoading = false) }
                    _isQueryError.update { errorState ->
                        errorState.copy(
                            isError = true,
                            errorMessage = response.e
                        )
                    }
                }
            }
        }
    }

}