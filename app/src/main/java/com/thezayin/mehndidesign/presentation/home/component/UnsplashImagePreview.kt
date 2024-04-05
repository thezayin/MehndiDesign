package com.thezayin.mehndidesign.presentation.home.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import com.thezayin.mehndidesign.domain.model.UnsplashImage
import com.thezayin.mehndidesign.domain.model.Urls
import com.thezayin.mehndidesign.domain.model.User
import com.thezayin.mehndidesign.domain.model.UserLinks

@ExperimentalCoilApi
@Composable
@Preview
fun UnsplashImagePreview() {
    UnSplashItem(
        unsplashImage = UnsplashImage(
            _id = 1,
            id = "1",
            urls = Urls(regular = "", thumb = "", small = "", full = ""),
            likes = 100,
            user = User(username = "unsplash template", userLinks = UserLinks(html = ""))
        )
    )
}