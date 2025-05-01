package com.thezayin.upload.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thezayin.upload.domain.usecase.UploadImagesUseCase
import com.thezayin.upload.presentation.event.AdminUploadEvent
import com.thezayin.upload.presentation.state.AdminUploadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AdminUploadViewModel(
    private val uploadImagesUseCase: UploadImagesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AdminUploadState>(AdminUploadState.Idle)
    val state: StateFlow<AdminUploadState> = _state

    fun onEvent(event: AdminUploadEvent) {
        when (event) {
            is AdminUploadEvent.SelectTable -> {
                _state.value = AdminUploadState.TableSelection(event.tableName)
            }
            is AdminUploadEvent.UploadImages -> {
                uploadImages(event.uris, event.tableName)
            }
        }
    }

    private fun uploadImages(uris: List<Uri>, tableName: String) {
        viewModelScope.launch {
            _state.value = AdminUploadState.Loading
            val result = uploadImagesUseCase.invoke(uris, tableName)
            _state.value = result.fold(
                onSuccess = { images -> AdminUploadState.Success(images) },
                onFailure = { throwable -> AdminUploadState.Error(throwable.localizedMessage ?: "Unknown Error") }
            )
        }
    }
}