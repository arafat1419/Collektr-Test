package com.arafat1419.collektr.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arafat1419.collektr.presentation.R
import com.arafat1419.collektr.presentation.ui.navigation.NavigationItem
import com.arafat1419.collektr.presentation.ui.theme.Secondary
import com.arafat1419.collektr.presentation.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigationItem: NavigationItem,
    actionIcon: ImageVector? = null,
    onActionClick: () -> Unit = {},
) {
    val topAppBarColors = TopAppBarColors(
        containerColor = if (navigationItem.appBarState == AppBarState.MAIN) Color.Transparent else Secondary,
        scrolledContainerColor = Secondary,
        navigationIconContentColor = White,
        titleContentColor = White,
        actionIconContentColor = White
    )

    val windowInsets = WindowInsets(
        top = 0.dp,
        bottom = 0.dp
    )

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        when (navigationItem.appBarState) {
            AppBarState.HOME -> {
                Image(
                    painter = painterResource(R.drawable.logo_collecktr_white),
                    contentDescription = stringResource(R.string.app_logo),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 40.dp, horizontal = 64.dp)
                )
            }

            AppBarState.MAIN -> {
                TopAppBar(
                    title = {
                        Text(
                            text = navigationItem.label,
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    colors = topAppBarColors,
                    windowInsets = windowInsets
                )
            }

            AppBarState.SUB -> {
                TopAppBar(
                    title = {
                        Text(
                            text = navigationItem.label,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    colors = topAppBarColors,
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = "Back Button",
                            )
                        }
                    },
                    actions = {
                        if (actionIcon != null) {
                            IconButton(
                                onClick = onActionClick
                            ) {
                                Icon(
                                    imageVector = actionIcon,
                                    contentDescription = "Action Button",
                                )
                            }
                        }
                    },
                    windowInsets = windowInsets
                )
            }
        }
    }
}

enum class AppBarState {
    HOME,
    MAIN,
    SUB
}
