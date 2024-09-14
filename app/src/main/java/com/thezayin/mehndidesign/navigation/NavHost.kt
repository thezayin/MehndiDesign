package com.thezayin.mehndidesign.navigation

import SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.thezayin.presentation.CategoryImageScreen
import com.thezayin.presentation.CategoryScreen
import com.thezayin.presentation.FavouriteScreen
import com.thezayin.presentation.HomeScreen
import com.thezayin.presentation.PreviewScreen
import com.thezayin.setting.SettingScreen

@Composable
fun NavHost(navController: NavHostController) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = SplashScreenNav
    ) {
        composable<SplashScreenNav> {
            SplashScreen(
                onNavigate = {
                    navController.navigate(HomeScreenNav)
                }
            )
        }
        composable<HomeScreenNav> {
            HomeScreen(
                onCategoryClick = { id, title ->
                    navController.navigate(CategoryImageScreenNav(id, title))
                },
                onMoreCategoryClick = {
                    navController.navigate(CategoryScreenNav)
                },
                onImageClick = { url ->
                    navController.navigate(PreviewScreenNav(url))
                },
                onSettingClick = {
                    navController.navigate(SettingScreenNav)
                },
                onLikeClick = {
                    navController.navigate(FavouriteScreenNav)
                },
            )
        }
        composable<CategoryScreenNav> {
            CategoryScreen(
                onBackClick = {
                    navController.navigateUp()
                },
                onCategoryClick = { id, title ->
                    navController.navigate(CategoryImageScreenNav(id, title))
                }
            )
        }
        // Category screen
        composable<CategoryImageScreenNav> {
            val args = it.toRoute<CategoryImageScreenNav>()
            CategoryImageScreen(
                id = args.id ?: 0,
                title = args.title ?: "",
                onBackClick = { navController.navigateUp() },
                onImageSelection = { url ->
                    navController.navigate(PreviewScreenNav(url))
                }
            )
        }
        // Favourite screen
        composable<FavouriteScreenNav> {
            FavouriteScreen(
                onBackClick = { navController.navigateUp() },
                onImageClick = { url ->
                    navController.navigate(PreviewScreenNav(url))
                }
            )
        }
        // Setting screen
        composable<SettingScreenNav> {
            SettingScreen(
                onBackClick = { navController.navigateUp() }
            )
        }
        //PreviewScreen
        composable<PreviewScreenNav> {
            val args = it.toRoute<PreviewScreenNav>()
            PreviewScreen(
                imageUrl = args.url ?: "",
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}