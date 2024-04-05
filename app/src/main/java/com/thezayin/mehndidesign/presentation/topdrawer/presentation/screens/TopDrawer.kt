package com.thezayin.mehndidesign.presentation.topdrawer.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.mehndidesign.presentation.activity.dialogs.LoadingDialog
import com.thezayin.mehndidesign.presentation.activity.dialogs.NetworkDialog
import com.thezayin.mehndidesign.presentation.destinations.CategoryComponentDestination
import com.thezayin.mehndidesign.presentation.topdrawer.presentation.component.DrawerComponent
import com.thezayin.mehndidesign.presentation.topdrawer.presentation.viewmodel.DrawerViewModel
import org.koin.compose.koinInject

@Composable
fun TopDrawer(navigator: DestinationsNavigator) {
    val viewModel: DrawerViewModel = koinInject()
    val isLoading = viewModel.isLoadingState.isLoading.value
    var checkNetwork by remember { mutableStateOf(false) }
    val state = rememberLazyListState()
    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (isLoading) {
        LoadingDialog()
    }

    val services = remember {
        viewModel.getHomeState.list
    }

    val serviceDataState = remember {
        DrawerViewModel.ServiceDataState()
    }
    serviceDataState.setNewList(services)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
    ) {
        Text(
            text = "Categories",
            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        LazyRow(
            state = state
        ) {
            items(serviceDataState.service, key = { it.id }) { item ->
                DrawerComponent(
                    item = item,
                    onItemClick = { drawerItem ->
                        when (drawerItem.title) {
                            "Bridal Mehndi" -> {
                                navigator.navigate(
                                    CategoryComponentDestination(
                                        query = "bridal henna",
                                        title = drawerItem.title
                                    )
                                )
                            }

                            "Eid Henna" -> {
                                navigator.navigate(
                                    CategoryComponentDestination(
                                        query = "pakistan mehndi",
                                        title = drawerItem.title
                                    )
                                )
                            }

                            "Punjabi Mehndi" -> {
                                navigator.navigate(
                                    CategoryComponentDestination(
                                        query = "hand mehndi",
                                        title = drawerItem.title
                                    )
                                )
                            }

                            "Pakistani Mehndi" -> {
                                navigator.navigate(
                                    CategoryComponentDestination(
                                        query = "mehndi tattos",
                                        title = drawerItem.title
                                    )
                                )
                            }

                            "Indian Henna" -> {
                                navigator.navigate(
                                    CategoryComponentDestination(
                                        query = "mehndi designs",
                                        title = drawerItem.title
                                    )
                                )
                            }

                            "Henna" -> {
                                navigator.navigate(
                                    CategoryComponentDestination(
                                        query = "henna tattoo",
                                        title = drawerItem.title
                                    )
                                )
                            }

                            "Jewellery Mehndi" -> {
                                navigator.navigate(
                                    CategoryComponentDestination(
                                        query = "Jewellery henna",
                                        title = drawerItem.title
                                    )
                                )
                            }

                            "Indian Mehndi" -> {
                                navigator.navigate(
                                    CategoryComponentDestination(
                                        query = "indian mehndi",
                                        title = drawerItem.title
                                    )
                                )
                            }

                            "Western Mehndi" -> {
                                navigator.navigate(
                                    CategoryComponentDestination(
                                        query = "jwellry tattoo",
                                        title = drawerItem.title
                                    )
                                )
                            }

                            "African Mehndi" -> {
                                navigator.navigate(
                                    CategoryComponentDestination(
                                        query = "arabic henna",
                                        title = drawerItem.title
                                    )
                                )
                            }

                            "Tattoo Mehndi" -> {
                                navigator.navigate(
                                    CategoryComponentDestination(
                                        query = "tattoo henna",
                                        title = drawerItem.title
                                    )
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}