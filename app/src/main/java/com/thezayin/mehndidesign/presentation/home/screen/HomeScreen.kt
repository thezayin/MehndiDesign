package com.thezayin.mehndidesign.presentation.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.mehndidesign.R
import com.thezayin.mehndidesign.presentation.activity.dialogs.NetworkDialog
import com.thezayin.mehndidesign.presentation.home.component.ListContent
import com.thezayin.mehndidesign.presentation.home.component.TopBarComponent
import com.thezayin.mehndidesign.presentation.home.viewmodel.HomeViewModel
import com.thezayin.mehndidesign.presentation.topdrawer.presentation.screens.TopDrawer
import org.koin.compose.koinInject

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val homeViewModel: HomeViewModel = koinInject()
    val getAllImages = homeViewModel.getAllImages.collectAsLazyPagingItems()

    var checkNetwork by remember { mutableStateOf(false) }

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        TopBarComponent(navigator = navigator)
        TopDrawer(navigator = navigator)
        Text(
            text = "Top Picks",
            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp, start = 10.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        ListContent(items = getAllImages, modifier = Modifier)
    }
}