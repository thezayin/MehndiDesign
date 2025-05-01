package com.thezayin.preview.presentation.state

import android.net.Uri
import com.thezayin.framework.model.Image

/**
 * UI state for the Preview screen.
 */
data class PreviewState(
    val downloadSuccess: Boolean = false,
    val image: Image?        = null,
    val imageUrl: String     = "",
    val isLiked: Boolean     = false,
    val savedUri: Uri?       = null,
    val shareUri: Uri?       = null,
    val error: String?       = null,
    val showDownloadAd: Boolean = true,
    val showShareAd: Boolean = true,
    val showBannerAd: Boolean = true,
    val isSaveSuccessDialogVisible: Boolean = false
)