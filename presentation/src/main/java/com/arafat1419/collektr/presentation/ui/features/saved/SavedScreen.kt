package com.arafat1419.collektr.presentation.ui.features.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arafat1419.collektr.presentation.ui.components.BaseLoading
import com.arafat1419.collektr.presentation.ui.components.List.AuctionList
import com.arafat1419.collektr.presentation.ui.components.TopAppBar
import com.arafat1419.collektr.presentation.ui.navigation.NavigationItem
import com.arafat1419.collektr.presentation.ui.theme.Primary
import com.arafat1419.collektr.presentation.ui.theme.White

@Composable
fun SavedScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SavedViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onTriggerEvent(SavedViewEvent.GetSavedAuctions)

        viewModel.uiEvent.collect { event ->
            when (event) {
                is SavedViewEvent.NavigateToDetails -> {
                    navController.navigate(NavigationItem.DetailAuction.route)
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
            navigationItem = NavigationItem.Saved
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(uiState.auctions, key = { it.id }) { auction ->
                AuctionList(
                    item = auction,
                    isActive = false,
                    onItemClick = {
                        viewModel.onTriggerEvent(SavedViewEvent.NavigateToDetails(it))
                    }
                )
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> BaseLoading()
            uiState.auctions.isEmpty() -> {
                Card(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = White,
                        contentColor = Primary
                    )
                ) {
                    Text(
                        text = "No saved auctions",
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

            uiState.error.isNotEmpty() -> Text(text = uiState.error)
        }
    }
}