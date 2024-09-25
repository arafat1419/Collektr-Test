package com.arafat1419.collektr.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.arafat1419.collektr.presentation.ui.enums.AppBar

enum class NavigationItem(
    val route: String,
    val label: String,
    val icon: ImageVector? = null,
    val appBar: AppBar
) {
    Home("home", "Home", Icons.Filled.Home, AppBar.HOME),
    Saved("saved", "Saved", Icons.Filled.Favorite, AppBar.MAIN),
    Profile("profile", "Profile", Icons.Filled.Person, AppBar.MAIN),

    LiveAuction("live_auction", "Live Auction", null, AppBar.SUB),
    DetailAuction("detail_auction", "Detail Auction", null, AppBar.SUB);

    object FullDetailArgs {
        internal const val AUCTION_ID = "auctionId"
    }
}