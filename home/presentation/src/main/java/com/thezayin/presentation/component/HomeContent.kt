package com.thezayin.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.paging.compose.LazyPagingItems
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.components.ErrorQueryDialog
import com.thezayin.components.NetworkDialog
import com.thezayin.databases.models.ImageModel
import com.thezayin.domain.model.CategoriesModel
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import com.thezayin.values.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * Main content for the home screen, displaying categories and images with proper error and network handling.
 *
 * @param isLoading Boolean indicating if the content is loading.
 * @param showError Boolean indicating if an error dialog should be shown.
 * @param nativeAd The current native ad to be displayed.
 * @param coroutineScope CoroutineScope for launching background tasks.
 * @param scrollState State of the lazy list for handling scrolling effects.
 * @param categories List of categories to display in the carousel.
 * @param networkStatus MutableState for network connectivity status.
 * @param imagePagingItems LazyPagingItems for paginated image content.
 * @param isCarouselVisible MutableState for controlling carousel visibility.
 * @param isImagesListVisible MutableState for controlling images list visibility.
 * @param fetchNativeAd Lambda to fetch a new native ad.
 * @param onLikeClick Callback function for like button clicks.
 * @param onSettingClick Callback function for settings button clicks.
 * @param dismissErrorDialog Lambda to hide the error dialog.
 * @param onImageClick Callback function for image clicks.
 * @param onMoreCategoryClick Callback function for more category button clicks.
 * @param onCategoryClick Callback function for category item clicks.
 */
@Composable
fun HomeContent(
    isLoading: Boolean,
    showError: Boolean,
    nativeAd: NativeAd?,
    coroutineScope: CoroutineScope,
    scrollState: LazyListState,
    categories: List<CategoriesModel>?,
    networkStatus: MutableState<Boolean>,
    imagePagingItems: LazyPagingItems<ImageModel>?,
    isCarouselVisible: MutableState<Boolean>,
    isImagesListVisible: MutableState<Boolean>,
    fetchNativeAd: () -> Unit,
    onLikeClick: () -> Unit,
    onSettingClick: () -> Unit,
    dismissErrorDialog: () -> Unit,
    onImageClick: (String) -> Unit,
    onMoreCategoryClick: () -> Unit,
    onCategoryClick: (Int, String?) -> Unit
) {
    // Trigger visibility animations for carousel and images once loading is complete
    LaunchedEffect(isLoading) {
        coroutineScope.launch {
            delay(300) // Short delay for smooth animation start
            isCarouselVisible.value = true
            delay(300) // Delay to ensure animations are visible
            isImagesListVisible.value = true
        }
    }

    // Lifecycle event handling for fetching native ads periodically
    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                coroutineScope.launch {
                    while (isActive) {
                        fetchNativeAd()
                        delay(20000L) // Fetch a new ad every 20 seconds
                    }
                }
            }

            else -> Unit // No action needed for other lifecycle events
        }
    }

    // Show network connectivity dialog if needed
    if (networkStatus.value) {
        NetworkDialog(showDialog = { networkStatus.value = it })
    }

    // Show error dialog if there is an error
    if (showError) {
        ErrorQueryDialog(
            showDialog = { dismissErrorDialog() },
            callback = {},
            error = "Unstable internet connection"
        )
    }

    // Display loading dialog with optional native ad
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
            nativeAd = { fetchNativeAd() },
            showAd = true
        )
    }

    // Main layout with Scaffold
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = {
            TopBar(
                settingCallback = onSettingClick,
                likeCallback = onLikeClick
            )
        },
        bottomBar = {
            GoogleNativeAd(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                style = GoogleNativeAdStyle.Small,
                nativeAd = nativeAd
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Animated visibility for category carousel
            AnimatedVisibility(
                visible = isCarouselVisible.value,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
                )
            ) {
                CategoryCarousel(
                    modifier = Modifier.fillMaxWidth(),
                    state = scrollState,
                    categories = categories,
                    onCategoryClick = { id, name ->
                        if (id == 5) {
                            onMoreCategoryClick()
                        } else {
                            onCategoryClick(id, name)
                        }
                    },
                )
            }

            // Animated visibility for image list
            AnimatedVisibility(
                visible = isImagesListVisible.value,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
                )
            ) {
                // Your code to display images (e.g., a LazyColumn or LazyRow with image items)
                // Example:
                ImagesList(
                    modifier = Modifier,
                    items = imagePagingItems,
                    onClick = onImageClick
                )
            }
        }
    }
}