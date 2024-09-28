package com.arafat1419.collektr.presentation.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.arafat1419.collektr.presentation.ui.components.AppBarState
import com.arafat1419.collektr.presentation.ui.theme.Black
import com.arafat1419.collektr.presentation.ui.theme.Primary
import com.arafat1419.collektr.presentation.ui.theme.White

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val bottomNavigationItems = NavigationItem.entries
        .filter { it.appBarState == AppBarState.HOME || it.appBarState == AppBarState.MAIN }

    NavigationBar(
        modifier = modifier,
        containerColor = Primary
    ) {
        bottomNavigationItems.forEach { bottomNavigation ->
            NavigationBarItem(
                selected = navController.currentDestination?.route == bottomNavigation.route,
                label = {
                    Text(
                        text = bottomNavigation.label,
                        fontWeight = FontWeight.Bold,
                    )
                },
                icon = {
                    if (bottomNavigation.icon != null) {
                        Icon(
                            bottomNavigation.icon,
                            contentDescription = bottomNavigation.label,
                            modifier = Modifier.semantics { testTag = bottomNavigation.label },
                        )
                    }
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = Primary,
                    selectedTextColor = White,
                    selectedIndicatorColor = White,
                    unselectedIconColor = Black,
                    unselectedTextColor = Black,
                    disabledIconColor = Color.Gray,
                    disabledTextColor = Color.Gray,
                ),
                onClick = {
                    navController.navigate(bottomNavigation.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

}