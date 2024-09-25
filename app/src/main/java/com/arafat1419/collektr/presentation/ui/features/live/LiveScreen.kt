package com.arafat1419.collektr.presentation.ui.features.live

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.arafat1419.collektr.presentation.ui.components.AppBar
import com.arafat1419.collektr.presentation.ui.navigation.NavigationItem

@Composable
fun LiveScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AppBar(
            modifier = modifier,
            navController = navController,
            navigationItem = NavigationItem.LiveAuction
        )
    }
}