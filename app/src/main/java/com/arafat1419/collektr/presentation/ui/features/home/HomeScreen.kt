package com.arafat1419.collektr.presentation.ui.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.arafat1419.collektr.presentation.ui.components.AppBar
import com.arafat1419.collektr.presentation.ui.navigation.NavigationItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        AppBar(
            modifier = modifier,
            navController = navController,
            navigationItem = NavigationItem.Home
        )
    }
}