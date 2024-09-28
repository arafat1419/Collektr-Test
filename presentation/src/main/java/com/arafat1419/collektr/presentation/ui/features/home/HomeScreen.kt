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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arafat1419.collektr.presentation.ui.components.AuctionCarousel
import com.arafat1419.collektr.presentation.ui.components.BaseLoading
import com.arafat1419.collektr.presentation.ui.components.TopAppBar
import com.arafat1419.collektr.presentation.ui.navigation.NavigationItem
import com.arafat1419.collektr.presentation.ui.theme.Primary
import com.arafat1419.collektr.presentation.ui.theme.White

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onTriggerEvent(HomeViewEvent.GetAuctionsCategories)

        viewModel.uiEvent.collect { event ->
            when (event) {
                is HomeViewEvent.NavigateToDetails -> {
                    navController.navigate(
                        NavigationItem.setAuctionId(
                            if (event.auction.isLive) {
                                NavigationItem.LiveAuction
                            } else {
                                NavigationItem.DetailAuction
                            },
                            event.auction.id
                        )
                    )
                }

                else -> {}
            }
        }
    }

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
            items(uiState.auctionCategories, key = { it.id }) { category ->
                val isSelected = uiState.selectedCategory == category
                SuggestionChip(
                    onClick = {
                        viewModel.onTriggerEvent(HomeViewEvent.GetAuctions(category.id))
                    },
                    label = {
                        Text(
                            text = category.name,
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                    modifier = Modifier
                        .padding(
                            start = if (uiState.auctionCategories.first() == category) 16.dp else 0.dp
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
            auctions = uiState.auctions,
        ) { auction ->
            viewModel.onTriggerEvent(HomeViewEvent.NavigateToDetails(auction))
        }
    }

    when {
        uiState.isLoading -> BaseLoading()
        uiState.error.isNotEmpty() -> Text(text = uiState.error)
    }
}