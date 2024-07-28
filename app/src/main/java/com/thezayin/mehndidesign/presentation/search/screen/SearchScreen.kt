package com.thezayin.mehndidesign.presentation.search.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.common.dialogs.NetworkDialog
import com.thezayin.mehndidesign.presentation.search.component.TopMenuSearchComponent
import com.thezayin.mehndidesign.presentation.search.component.TopSearchBarComponent
import com.thezayin.mehndidesign.presentation.search.viewmodel.SearchViewModel
import org.koin.compose.koinInject

@Destination
@Composable
fun SearchScreen(navigator: DestinationsNavigator) {
    val searchViewModel: SearchViewModel = koinInject()
    val searchQuery by searchViewModel.searchQuery

    var checkNetwork by remember { mutableStateOf(false) }

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .navigationBarsPadding()
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            TopMenuSearchComponent(
                modifier = Modifier.padding(horizontal = 20.dp),
                navigator = navigator
            )
            TopSearchBarComponent(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .padding(horizontal = 20.dp),
                searchViewModel = searchViewModel
            )

            Text(
                text = "Search Results",
                modifier = Modifier.padding(top = 20.dp, start = 20.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

        }
    }
}