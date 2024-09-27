package com.arafat1419.collektr.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.arafat1419.collektr.presentation.ui.components.AppBarState
import com.arafat1419.collektr.presentation.ui.navigation.BottomNavigationBar
import com.arafat1419.collektr.presentation.ui.navigation.MainNavHost
import com.arafat1419.collektr.presentation.ui.navigation.NavigationItem
import com.arafat1419.collektr.presentation.ui.theme.CollektrTheme
import com.arafat1419.collektr.presentation.utils.PresentationUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CollektrTheme {
                App()
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navItem = NavigationItem.entries.firstOrNull { it.route == currentRoute }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { },
        bottomBar = {
            if (navItem?.appBarState != AppBarState.SUB) {
                BottomNavigationBar(
                    modifier = modifier,
                    navController = navController,
                )
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PresentationUtils.getBasicVerticalGradient())
        ) {
            MainNavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}