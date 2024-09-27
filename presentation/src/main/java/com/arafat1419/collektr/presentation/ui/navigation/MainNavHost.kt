package com.arafat1419.collektr.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arafat1419.collektr.presentation.ui.features.detail.DetailScreen
import com.arafat1419.collektr.presentation.ui.features.home.HomeScreen
import com.arafat1419.collektr.presentation.ui.features.live.LiveScreen
import com.arafat1419.collektr.presentation.ui.features.profile.ProfileScreen
import com.arafat1419.collektr.presentation.ui.features.saved.SavedScreen

@Composable
fun MainNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route,
        modifier = modifier
    ) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(NavigationItem.Saved.route) {
            SavedScreen(navController = navController)
        }
        composable(NavigationItem.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(NavigationItem.LiveAuction.route) {
            LiveScreen(navController = navController)
        }
        composable(NavigationItem.DetailAuction.route) {
            DetailScreen(navController = navController)
        }
    }
}