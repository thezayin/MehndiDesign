package com.thezayin.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.databases.models.LikeImageModel
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * Composable function to display the content of the Favourite screen.
 *
 * @param isLoading Boolean indicating if the data is still loading.
 * @param nativeAd Native ad to be displayed in the UI.
 * @param scope Coroutine scope for launching coroutines.
 * @param list List of favorite images.
 * @param noItemVisible MutableState to control visibility of "No items" message.
 * @param getNativeAd Function to load or refresh a native ad.
 * @param onBackClick Callback function to handle back navigation.
 * @param onImageClick Callback function to handle image clicks.
 */

@Composable
fun FavouriteScreenContent(
    isLoading: Boolean,
    nativeAd: NativeAd?,
    scope: CoroutineScope,
    list: List<LikeImageModel>,
    noItemVisible: MutableState<Boolean>,
    getNativeAd: () -> Unit,
    onBackClick: () -> Unit,
    onImageClick: (String) -> Unit
) {
    // Update the visibility state of the "No items" message based on the list content
    LaunchedEffect(list.isEmpty()) {
        noItemVisible.value = list.isEmpty()
    }

    // Handle lifecycle events for ad refreshing
    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                scope.launch {
                    while (isActive) {
                        getNativeAd()
                        delay(20000L) // Refresh ad every 20 seconds
                    }
                }
            }

            else -> Unit
        }
    }

    // Display a loading dialog with an ad while content is loading
    if (isLoading) {
        com.thezayin.components.LoadingDialog(
            ad = {
                GoogleNativeAd(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    nativeAd = nativeAd,
                    style = GoogleNativeAdStyle.Small
                )
            },
            nativeAd = {
                getNativeAd()
            },
            showAd = true
        )
    }

    // Main screen layout
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = com.thezayin.values.R.color.background),
        topBar = {
            FavoriteTopBar(
                modifier = Modifier,
                screenTitle = "My Favorites",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            GoogleNativeAd(
                modifier = Modifier,
                style = GoogleNativeAdStyle.Small,
                nativeAd = nativeAd
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (list.isEmpty()) {
                AnimatedVisibility(
                    visible = noItemVisible.value,
                    enter = slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
                    ),
                ) {
                    NoItemPresent(Modifier)
                }
            } else {
                ImageList(
                    modifier = Modifier,
                    items = list,
                    onImageClick = { model ->
                        model.URL?.let(onImageClick)
                    }
                )
            }
        }
    }
}