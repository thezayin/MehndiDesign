package com.thezayin.mehndidesign.presentation.home.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.thezayin.mehndidesign.domain.model.UnsplashImage
import com.thezayin.mehndidesign.presentation.activity.dialogs.LoadingDialog

@Composable
fun ListContent(items: LazyPagingItems<UnsplashImage>, modifier: Modifier) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
    ) {
        items(
            count = items.itemCount,
            key = { index -> items[index]?._id ?: index },
        ) {
            val item = items[it]
            if (items.itemCount > 0) {
                UnSplashItem(unsplashImage = item)
            } else {
                LoadingDialog()
            }
        }
    }
}