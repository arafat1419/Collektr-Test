package com.arafat1419.collektr.presentation.ui.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arafat1419.collektr.presentation.ui.components.AuctionCarousel
import com.arafat1419.collektr.presentation.ui.components.TopAppBar
import com.arafat1419.collektr.presentation.ui.navigation.NavigationItem
import com.arafat1419.collektr.presentation.ui.theme.Primary
import com.arafat1419.collektr.presentation.ui.theme.White

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val categories by remember { mutableStateOf(AuctionCategory.generateDummyData()) }
    var selectedCategory by remember { mutableStateOf(AuctionCategory.generateDummyData()[0]) }

    val auctions by remember { mutableStateOf(AuctionItem.generateDummyData()) }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            modifier = modifier,
            navController = navController,
            navigationItem = NavigationItem.Home
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories, key = { it.id }) { category ->
                val isSelected = selectedCategory == category
                SuggestionChip(
                    onClick = {
                        selectedCategory = category
                    },
                    label = {
                        Text(
                            text = category.title,
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                    modifier = Modifier
                        .padding(
                            start = if (categories.first() == category) 16.dp else 0.dp
                        ),
                    shape = RoundedCornerShape(8.dp),
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = if (isSelected) Primary else Color.Transparent,
                        labelColor = White,
                    ),
                    border = SuggestionChipDefaults.suggestionChipBorder(
                        enabled = !isSelected,
                        borderColor = if (isSelected) Color.Transparent else White,
                        borderWidth = 1.dp
                    )
                )
            }
        }

        AuctionCarousel(
            auctions = auctions,
        ) { auction ->
            if (auction.isLive) {
                navController.navigate(NavigationItem.LiveAuction.route)
            } else {
                navController.navigate(NavigationItem.DetailAuction.route)
            }
        }
    }
}

data class AuctionCategory(
    val id: Int,
    val title: String,
) {
    companion object {
        fun generateDummyData(): List<AuctionCategory> = listOf(
            AuctionCategory(1, "Magic The Gathering"),
            AuctionCategory(2, "Accessories"),
            AuctionCategory(3, "Hot Wheels"),
        )
    }
}

data class AuctionItem(
    val id: Int,
    val category: AuctionCategory,
    val imageUrl: String,
    val title: String,
    val auctionEnd: String,
    val highestBid: Long,
    val isLive: Boolean
) {
    companion object {
        fun generateDummyData(): List<AuctionItem> = listOf(
            AuctionItem(
                id = 1,
                category = AuctionCategory.generateDummyData()[0],
                imageUrl = "https://cllktr.s3.ap-southeast-1.amazonaws.com/product/01J8JCQ12QW5F9QN4JZG9QWCSQ-collektr.webp",
                title = "Sergeant Tibbs - Courageous Cat",
                auctionEnd = "04:10:10",
                highestBid = 100,
                isLive = false
            ),
            AuctionItem(
                id = 2,
                category = AuctionCategory.generateDummyData()[0],
                imageUrl = "https://cllktr.s3.ap-southeast-1.amazonaws.com/product/01J8JCRKXAPB2Z47B6GWVRS7KK-collektr.webp",
                title = "Moana - Undeterred Voyager",
                auctionEnd = "02:10:10",
                highestBid = 150,
                isLive = true
            ),
            AuctionItem(
                id = 3,
                category = AuctionCategory.generateDummyData()[0],
                imageUrl = "https://cllktr.s3.ap-southeast-1.amazonaws.com/product/01J8JCN84033XTGHVYTWBVJ57A-collektr.webp",
                title = "Scar - Fiery Usurper",
                auctionEnd = "01:10:10",
                highestBid = 2000,
                isLive = false
            ),
        )
    }
}