package com.thezayin.preview.presentation.event

/**
 * Events for the Preview screen.
 */
sealed class PreviewEvent {
    object Load : PreviewEvent()
    object Back : PreviewEvent()
    object Like : PreviewEvent()
    object Share : PreviewEvent()
    object Download : PreviewEvent()
    object ResetDownloadFlag : PreviewEvent()
    object ResetShareFlag     : PreviewEvent()
    data class ShowSaveSuccessDialog(val show: Boolean) : PreviewEvent()
    data class ShowBannerAd(val show: Boolean) : PreviewEvent()
    data class ShowDownloadAd(val show: Boolean) : PreviewEvent()
    data class ShowShareAd(val show: Boolean) : PreviewEvent()
}