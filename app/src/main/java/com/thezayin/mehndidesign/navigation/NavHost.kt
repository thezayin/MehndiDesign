package com.thezayin.mehndidesign.navigation

import SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.thezayin.category.presentation.CategoryScreen
import com.thezayin.favorites.presentation.FavoritesScreen
import com.thezayin.homes.presentation.HomeScreen
import com.thezayin.preview.presentation.PreviewScreen
import com.thezayin.start_up.onboarding.OnboardingScreen
import com.thezayin.start_up.setting.SettingScreen

@Composable
fun NavHost(navController: NavHostController) {
    androidx.navigation.compose.NavHost(
        navController = navController, startDestination = SplashScreenNav
    ) {
        composable<SplashScreenNav> {
            SplashScreen(
                navigateToOnboarding = {
                    navController.navigate(OnboardingScreenNav) {
                        popUpTo(SplashScreenNav) {
                            inclusive = true
                        }
                    }
                },
                navigateToHome = {
                    navController.navigate(HomeScreenNav)
                })
        }
        composable<HomeScreenNav> {
            HomeScreen(
                onSettingClick = {
                    navController.navigate(SettingScreenNav)
                }, onImageClick = {
                    navController.navigate(PreviewScreenNav)
                }, onLikeClick = {
                    navController.navigate(FavouriteScreenNav)
                }, onCategoryClick = {
                    navController.navigate(CategoryScreenNav)
                })
        }
        composable<CategoryScreenNav> {
            CategoryScreen(onBack = {
                navController.navigateUp()
            }, onPreview = {
                navController.navigate(PreviewScreenNav)
            })
        }

        composable<FavouriteScreenNav> {
            FavoritesScreen(onBackClick = { navController.navigateUp() }, onPreviewClick = {
                navController.navigate(PreviewScreenNav)
            })
        }
        composable<SettingScreenNav> {
            SettingScreen(
                onBackClick = { navController.navigateUp() })
        }
        composable<PreviewScreenNav> {
            PreviewScreen(
                onBack = { navController.navigateUp() })
        }
        composable<OnboardingScreenNav> {
            OnboardingScreen(
                navigateToHome = {
                    navController.navigate(HomeScreenNav)
                }
            )
        }
    }
}
