package com.arafat1419.collektr.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.arafat1419.collektr.presentation.ui.components.AppBarState

enum class NavigationItem(
    val route: String,
    val label: String,
    val icon: ImageVector? = null,
    val appBarState: AppBarState
) {
    Home("home", "Home", Icons.Filled.Home, AppBarState.HOME),
    Saved("saved", "Saved", Icons.Filled.Favorite, AppBarState.MAIN),
    Profile("profile", "Profile", Icons.Filled.Person, AppBarState.MAIN),

    LiveAuction("live_auction", "Live Auction", null, AppBarState.SUB),
    DetailAuction("detail_auction", "Detail Auction", null, AppBarState.SUB);

    object FullDetailArgs {
        internal const val AUCTION_ID = "auctionId"
    }
}