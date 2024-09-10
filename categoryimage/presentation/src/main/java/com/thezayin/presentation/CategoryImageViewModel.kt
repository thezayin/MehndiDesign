package com.thezayin.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.ads.GoogleManager
import com.thezayin.domain.usecase.ArabicImages
import com.thezayin.domain.usecase.ArabicRemote
import com.thezayin.domain.usecase.BridalImages
import com.thezayin.domain.usecase.BridalRemote
import com.thezayin.domain.usecase.ClassicImages
import com.thezayin.domain.usecase.ClassicRemote
import com.thezayin.domain.usecase.FingerImages
import com.thezayin.domain.usecase.FingerRemote
import com.thezayin.domain.usecase.FootImages
import com.thezayin.domain.usecase.FootRemote
import com.thezayin.domain.usecase.IndianImages
import com.thezayin.domain.usecase.IndianRemote
import com.thezayin.domain.usecase.IndoImages
import com.thezayin.domain.usecase.IndoRemote
import com.thezayin.domain.usecase.MoroccanImages
import com.thezayin.domain.usecase.MoroccanRemote
import com.thezayin.domain.usecase.PakistaniImages
import com.thezayin.domain.usecase.PakistaniRemote
import com.thezayin.domain.usecase.TattooImages
import com.thezayin.domain.usecase.TattooRemote
import com.thezayin.domain.usecase.TikkiImages
import com.thezayin.domain.usecase.TikkiRemote
import com.thezayin.framework.utils.Response
import com.thezayin.presentation.event.CategoryImageEvent
import com.thezayin.presentation.state.CategoryImageState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryImageViewModel(
    private val googleManager: GoogleManager,
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

    private val _categoryImages = MutableStateFlow(CategoryImageState.CategoryImageUiState())
    val categoryImages = _categoryImages.asStateFlow()


    var nativeAd = mutableStateOf<NativeAd?>(null)
        private set

    fun getNativeAd() = viewModelScope.launch {
        nativeAd.value = googleManager.createNativeAd().apply {} ?: run {
            delay(3000)
            googleManager.createNativeAd()
        }
    }

    private fun categoryEvent(event: CategoryImageEvent) {
        when (event) {

            is CategoryImageEvent.ErrorMessage -> {
                _categoryImages.update {
                    it.copy(errorMessage = event.errorMessage)
                }
            }

            is CategoryImageEvent.ImagesList -> {
                _categoryImages.update {
                    it.copy(images = event.result)
                }
            }

            CategoryImageEvent.HideErrorDialog -> {
                _categoryImages.update {
                    it.copy(showError = false)
                }
            }

            CategoryImageEvent.HideLoading -> {
                _categoryImages.update {
                    it.copy(isLoading = false)
                }
            }

            CategoryImageEvent.ShowError -> {
                _categoryImages.update {
                    it.copy(showError = true)
                }
            }

            CategoryImageEvent.ShowLoading -> {
                _categoryImages.update {
                    it.copy(isLoading = true)
                }
            }
        }
    }

    fun fetchRemoteTattoo() = viewModelScope.launch {
        tattooRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchTattooImages()
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

    fun fetchRemotePakistani() = viewModelScope.launch {
        pakistaniRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchPakistaniImages()
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

    fun fetchRemoteMoroccan() = viewModelScope.launch {
        moroccanRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchMoroccanImages()
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

    fun fetchRemoteIndo() = viewModelScope.launch {
        indoRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchIndoImages()
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

    fun fetchRemoteFoot() = viewModelScope.launch {
        footRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchFootImages()
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

    fun fetchRemoteIndian() = viewModelScope.launch {
        indianRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchIndianImages()
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

    fun fetchRemoteFinger() = viewModelScope.launch {
        fingerRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchFingerImages()
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

    fun fetchRemoteClassic() = viewModelScope.launch {
        classicRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchClassicImages()
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

    fun fetchRemoteTikki() = viewModelScope.launch {
        tikkiRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchTikkiImages()
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

    fun fetchRemoteBridal() = viewModelScope.launch {
        bridalRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchBridalImages()
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

    fun fetchRemoteArabic() = viewModelScope.launch {
        arabicRemote().collect { response ->
            when (response) {
                is Response.Success -> {
                    fetchArabicImages()
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

    private fun fetchTattooImages() = viewModelScope.launch {
        tattoo().collect { response ->
            when (response) {
                is Response.Success -> {
                    imagesList(response.data.map { data ->
                        data.map {
                            it.URL
                        }
                    })
                    delay(3000L)
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

    private fun fetchPakistaniImages() = viewModelScope.launch {
        pakistani().collect { response ->
            when (response) {
                is Response.Success -> {
                    imagesList(response.data.map { data ->
                        data.map {
                            it.URL
                        }
                    })
                    delay(3000L)
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

    private fun fetchMoroccanImages() = viewModelScope.launch {
        moroccan().collect { response ->
            when (response) {
                is Response.Success -> {
                    imagesList(response.data.map { data ->
                        data.map {
                            it.URL
                        }
                    })
                    delay(3000L)
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

    private fun fetchIndoImages() = viewModelScope.launch {
        indo().collect { response ->
            when (response) {
                is Response.Success -> {
                    imagesList(response.data.map { data ->
                        data.map {
                            it.URL
                        }
                    })
                    delay(3000L)
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

    private fun fetchFootImages() = viewModelScope.launch {
        foot().collect { response ->
            when (response) {
                is Response.Success -> {
                    imagesList(response.data.map { data ->
                        data.map {
                            it.URL
                        }
                    })
                    delay(3000L)
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

    private fun fetchIndianImages() = viewModelScope.launch {
        indian().collect { response ->
            when (response) {
                is Response.Success -> {
                    imagesList(response.data.map { data ->
                        data.map {
                            it.URL
                        }
                    })
                    delay(3000L)
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

    private fun fetchFingerImages() = viewModelScope.launch {
        finger().collect { response ->
            when (response) {
                is Response.Success -> {
                    imagesList(response.data.map { data ->
                        data.map {
                            it.URL
                        }
                    })
                    delay(3000L)
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

    private fun fetchClassicImages() = viewModelScope.launch {
        classic().collect { response ->
            when (response) {
                is Response.Success -> {
                    imagesList(response.data.map { data ->
                        data.map {
                            it.URL
                        }
                    })
                    delay(3000L)
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

    private fun fetchTikkiImages() = viewModelScope.launch {
        tikki().collect { response ->
            when (response) {
                is Response.Success -> {
                    imagesList(response.data.map { data ->
                        data.map {
                            it.URL
                        }
                    })
                    delay(3000L)
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

    private fun fetchBridalImages() = viewModelScope.launch {
        bridal().collect { response ->
            when (response) {
                is Response.Success -> {
                    imagesList(response.data.map { data ->
                        data.map {
                            it.URL
                        }
                    })
                    delay(3000L)
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

    private fun fetchArabicImages() = viewModelScope.launch {
        arabic().collect { response ->
            when (response) {
                is Response.Success -> {
                    imagesList(response.data.map { data ->
                        data.map {
                            it.URL
                        }
                    })
                    delay(3000L)
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

    private fun imagesList(images: Flow<PagingData<String>>) {
        categoryEvent(CategoryImageEvent.ImagesList(images))
    }

    private fun errorMessages(error: String) {
        categoryEvent(CategoryImageEvent.ErrorMessage(error))
    }

    private fun showError() {
        categoryEvent(CategoryImageEvent.ShowError)
    }

    fun hideErrorDialog() {
        categoryEvent(CategoryImageEvent.HideErrorDialog)
    }

    private fun hideLoading() {
        categoryEvent(CategoryImageEvent.HideLoading)
    }

    private fun showLoading() {
        categoryEvent(CategoryImageEvent.ShowLoading)
    }
}