package com.thezayin.mehndidesign.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.thezayin.presentation.CategoriesListScreen
import com.thezayin.presentation.CategoryScreen
import com.thezayin.presentation.FavouriteScreen
import com.thezayin.presentation.HomeScreen
import com.thezayin.presentation.PreviewScreen
import com.thezayin.setting.SettingScreen
import com.thezayin.splash.SplashScreen

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
                    navController.navigate(CategoryScreenNav(id, title))
                },
                onMoreCategoryClick = {
                    navController.navigate(CategoryListScreenNav)
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
        composable<CategoryListScreenNav> {
            CategoriesListScreen(
                onBackClick = {
                    navController.navigateUp()
                },
                onCategoryClick = { id, title ->
                    navController.navigate(CategoryScreenNav(id, title))
                }
            )
        }
        // Category screen
        composable<CategoryScreenNav> {
            val args = it.toRoute<CategoryScreenNav>()
            CategoryScreen(
                id = args.id ?: 0,
                title = args.title ?: "",
                onBackClick = { navController.navigateUp() }
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
                url = args.url ?: "",
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}