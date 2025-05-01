package com.thezayin.upload.presentation.state

import com.thezayin.upload.domain.model.ImageModel

sealed class AdminUploadState {
    object Idle : AdminUploadState()
    object Loading : AdminUploadState()
    data class Success(val images: List<ImageModel>) : AdminUploadState()
    data class Error(val message: String) : AdminUploadState()
    data class TableSelection(val selectedTable: String) : AdminUploadState() // Add this state
}