package com.thezayin.mehndidesign.presentation.topdrawer.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.mehndidesign.R
import com.thezayin.mehndidesign.presentation.activity.dialogs.LoadingDialog
import com.thezayin.mehndidesign.presentation.home.component.ListContent
import com.thezayin.mehndidesign.presentation.topdrawer.presentation.viewmodel.DrawerViewModel
import org.koin.compose.koinInject

@Destination
@Composable
fun CategoryComponent(
    navigator: DestinationsNavigator,
    query: String,
    title:String
) {
    val drawerViewModel: DrawerViewModel = koinInject()
    LaunchedEffect(key1 = Unit) {
        drawerViewModel.searchQuery(query)
    }
    val searchedImages = drawerViewModel.searchedImages.collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(color = colorResource(id = R.color.background))
    ) {
        TopDrawerBar(modifier = Modifier.padding(horizontal = 20.dp), navigator = navigator, string = title)
        if (searchedImages.itemCount > 0) {
            ListContent(
                items = searchedImages,
                modifier = Modifier.padding(top = 30.dp)
            )
        } else {
            LoadingDialog()
        }
    }
}