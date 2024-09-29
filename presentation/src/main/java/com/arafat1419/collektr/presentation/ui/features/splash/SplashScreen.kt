package com.arafat1419.collektr.presentation.ui.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.arafat1419.collektr.presentation.R
import com.arafat1419.collektr.presentation.ui.navigation.NavigationItem
import com.arafat1419.collektr.presentation.utils.PresentationUtils
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        delay(2000L)
        navController.navigate(NavigationItem.Home.route) {
            popUpTo(NavigationItem.Splash.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PresentationUtils.getBasicVerticalGradient()),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo_collecktr_white),
            contentDescription = null,
            modifier.fillMaxSize(0.5f)
        )
    }
}