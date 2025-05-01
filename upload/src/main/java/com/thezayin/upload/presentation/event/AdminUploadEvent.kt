package com.thezayin.upload.presentation.event

import android.net.Uri

sealed class AdminUploadEvent {
    data class SelectTable(val tableName: String) : AdminUploadEvent()
    data class UploadImages(val uris: List<Uri>, val tableName: String) : AdminUploadEvent()
}