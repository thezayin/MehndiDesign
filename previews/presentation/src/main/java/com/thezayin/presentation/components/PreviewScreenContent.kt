package com.thezayin.presentation.components

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.components.LoadingDialog
import com.thezayin.components.SaveImageDialog
import com.thezayin.components.SaveImageSuccessDialog
import com.thezayin.domain.model.PreviewMenu
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * Composable function that displays the content for the Preview Screen Content.
 *
 * @param imageId ID of the image to manage favorite actions.
 * @param imageUrl URL of the image to display.
 * @param menuItems List of menu items for the image.
 * @param activity Current activity context to show Toasts.
 * @param showLoading Boolean flag to display loading dialog.
 * @param currentNativeAd Native ad object to be shown.
 * @param imageExistsInFavorites Boolean flag to check if the image is in favorites.
 * @param imageSavedSuccess Boolean flag to indicate if the image was saved successfully.
 * @param coroutineScope Scope for coroutine operations.
 * @param onBackButtonClick Action to perform when the back button is clicked.
 * @param fetchNativeAd Function to fetch a native ad.
 * @param removeImageFromFavorites Function to remove the image from favorites.
 * @param addImageToFavorites Function to add the image to favorites.
 * @param verifyImageExistenceInFavorites Function to check if the image exists in favorites.
 */
@Composable
fun PreviewScreenContent(
    imageId: Int?,
    imageUrl: String,
    menuItems: List<PreviewMenu>,
    activity: Activity,
    showLoading: Boolean,
    showLoadingAd: Boolean,
    showBottomAd: Boolean,
    currentNativeAd: NativeAd?,
    isSavingImage: Boolean,
    saveImageMessage: String?,
    saveImageSuccess: Boolean,
    imageExistsInFavorites: Boolean,
    imageSavedSuccess: Boolean,
    coroutineScope: CoroutineScope,
    onBackButtonClick: () -> Unit,
    fetchNativeAd: () -> Unit,
    removeImageFromFavorites: (Int) -> Unit,
    addImageToFavorites: (String) -> Unit,
    verifyImageExistenceInFavorites: () -> Unit,
    saveImage: () -> Unit,
    resetSaveImageMessage: () -> Unit
) {

//    // Show save progress dialog
//    if (isSavingImage) {
//        SaveImageDialog(isSaving = isSavingImage, saveMessage = saveImageMessage)
//    }
//
//    // Show save success dialog
//    if (saveImageSuccess) {
//        SaveImageSuccessDialog(resetSaveState = resetSaveImageMessage)
//    }

    // Handle the saveImageMessage
    if (saveImageMessage != null) {
        LaunchedEffect(saveImageMessage) {
            Toast.makeText(activity, saveImageMessage, Toast.LENGTH_SHORT).show()
            resetSaveImageMessage() // Reset the message after displaying
        }
    }

    // Rest of the composable...

    // Display a toast if the image was successfully saved
    if (imageSavedSuccess) {
        Toast.makeText(activity, "Image saved successfully", Toast.LENGTH_SHORT).show()
    }


    // Check if the image exists in favorites when the composable is launched
    LaunchedEffect(Unit) {
        verifyImageExistenceInFavorites()
    }

    // Display a toast if the image was successfully saved
    if (imageSavedSuccess) {
        Toast.makeText(activity, "Image saved successfully", Toast.LENGTH_SHORT).show()
    }

    // Show a loading dialog if data is being loaded, along with the native ad
    if (showLoading) {
        LoadingDialog(
            ad = {
                GoogleNativeAd(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    nativeAd = currentNativeAd,
                    style = GoogleNativeAdStyle.Small
                )
            },
            nativeAd = { fetchNativeAd() },
            showAd = showLoadingAd
        )
    }

    // Handle lifecycle events, particularly for refreshing the native ad periodically
    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                coroutineScope.launch {
                    // Refresh the native ad every 2 seconds when the composable is active
                    while (this.isActive) {
                        fetchNativeAd()
                        delay(2000L)
                    }
                }
            }

            else -> {
                // Other lifecycle events can be handled here if needed
            }
        }
    }

    // Main scaffold layout of the preview screen
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = com.thezayin.values.R.color.background),
        topBar = {
            TopBar(
                modifier = Modifier,
                screenTitle = "Image Preview",
                onBackClick = onBackButtonClick
            )
        },
        bottomBar = {
            // Display a native ad at the bottom of the screen
            if (showBottomAd) {
                GoogleNativeAd(
                    modifier = Modifier,
                    style = GoogleNativeAdStyle.Small,
                    nativeAd = currentNativeAd
                )
            }
        }
    ) { padding ->
        // Display the image and handle actions from the menu
        Column(modifier = Modifier.padding(padding)) {
            ImagePreview(
                imageUrl = imageUrl,
                menuItems = menuItems
            ) { selectedMenuOption ->
                when (selectedMenuOption) {
                    1 -> {
                        // Placeholder for the first menu option (if needed in future)
                        saveImage()
                    }

                    2 -> {
                        // Handle adding or removing the image from favorites based on its current state
                        if (imageExistsInFavorites) {
                            imageId?.let {
                                removeImageFromFavorites(it)
                                Toast.makeText(
                                    activity, "Image removed from favorites", Toast.LENGTH_SHORT
                                ).show()
                                onBackButtonClick() // Navigate back after removal
                            }
                        } else {
                            addImageToFavorites(imageUrl)
                            Toast.makeText(
                                activity, "Image added to your favorites", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}
