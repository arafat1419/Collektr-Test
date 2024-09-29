package com.arafat1419.collektr.presentation.ui.features.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.arafat1419.collektr.presentation.R
import com.arafat1419.collektr.presentation.ui.components.BaseLoading
import com.arafat1419.collektr.presentation.ui.components.BottomMessageAndBid
import com.arafat1419.collektr.presentation.ui.components.CreatorProfile
import com.arafat1419.collektr.presentation.ui.components.List.MessageAndBidList
import com.arafat1419.collektr.presentation.ui.components.MessageAndBidItem
import com.arafat1419.collektr.presentation.ui.components.PlaceBidBottomSheet
import com.arafat1419.collektr.presentation.ui.components.TopAppBar
import com.arafat1419.collektr.presentation.ui.navigation.NavigationItem
import com.arafat1419.collektr.presentation.ui.theme.LightWhite
import com.arafat1419.collektr.presentation.ui.theme.Primary
import com.arafat1419.collektr.presentation.ui.theme.White
import com.arafat1419.collektr.presentation.utils.PresentationUtils
import com.arafat1419.collektr.presentation.utils.PresentationUtils.moneyFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel(),
    auctionId: Int?
) {
    val uiState by viewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(Unit) {
        if (auctionId == null) {
            navController.popBackStack()
            return@LaunchedEffect
        }

        viewModel.onTriggerEvent(DetailViewEvent.GetAuctionDetail(auctionId))

        viewModel.uiEvent.collect { event ->
            when (event) {
                DetailViewEvent.ShowPlaceBidBottomSheet -> sheetState.show()
                DetailViewEvent.HidePlaceBidBottomSheet -> sheetState.hide()
                else -> {}
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            TopAppBar(
                modifier = modifier,
                navController = navController,
                navigationItem = NavigationItem.DetailAuction,
                actionIcon =
                if (uiState.auction.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
            ) {
                viewModel.onTriggerEvent(
                    DetailViewEvent.SetFavoriteAuction(
                        auctionId!!,
                        !uiState.auction.isFavorite
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = uiState.auction.img,
                    contentDescription = stringResource(R.string.auction_item),
                    modifier = Modifier
                        .padding(horizontal = 64.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.FillWidth
                )

                uiState.highestBid.let {
                    if (it.bidAmount > 0) {
                        MessageAndBidItem(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .fillMaxWidth()
                                .background(Primary.copy(alpha = 0.6F))
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            icon = painterResource(R.drawable.outline_star_rate_24),
                            title = stringResource(R.string.highest_bid),
                            message = "${it.userName} (RM${it.bidAmount.moneyFormat()})"
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(start = 16.dp, top = 26.dp, end = 16.dp)
                ) {
                    Text(
                        text = uiState.auction.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = White
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = stringResource(R.string.creator),
                                style = MaterialTheme.typography.labelSmall,
                                color = LightWhite
                            )

                            CreatorProfile(
                                modifier = Modifier
                                    .padding(top = 4.dp),
                                creator = uiState.auction.creator
                            )
                        }

                        Column {
                            Text(
                                text = stringResource(R.string.ending_in),
                                style = MaterialTheme.typography.labelSmall,
                                color = LightWhite
                            )

                            Text(
                                text = PresentationUtils.convertTimestampToDateTime(uiState.auction.auctionEnd),
                                modifier = Modifier.padding(top = 10.dp),
                                style = MaterialTheme.typography.titleSmall,
                                color = White
                            )
                        }
                    }

                    Text(
                        text = stringResource(R.string.description),
                        modifier = Modifier.padding(top = 16.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = LightWhite
                    )

                    Text(
                        text = uiState.auction.description,
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.titleSmall,
                        color = White
                    )

                    if (uiState.chatBids.isNotEmpty()) {
                        Text(
                            text = stringResource(R.string.live_chat_bidding),
                            modifier = Modifier.padding(top = 16.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = LightWhite
                        )

                        MessageAndBidList(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth()
                                .height(((uiState.chatBids.size.coerceAtMost(5)) * 40).dp),
                            chatBids = uiState.chatBids
                        )
                    }

                    Spacer(modifier = Modifier.height(112.dp))
                }
            }
        }

        BottomMessageAndBid(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            message = uiState.chatMessage,
            onMessageChange = {
                viewModel.onTriggerEvent(
                    DetailViewEvent.OnChatMessageChange(
                        it
                    )
                )
            },
            onSendClicked = {
                viewModel.onTriggerEvent(
                    DetailViewEvent.SendMessage(
                        auctionId!!,
                        uiState.chatMessage
                    )
                )
            }
        ) {
            viewModel.onTriggerEvent(DetailViewEvent.ShowPlaceBidBottomSheet)
        }
    }

    if (sheetState.isVisible) {
        PlaceBidBottomSheet(
            sheetState = sheetState,
            bidAmount = uiState.bidAmount,
            error = uiState.bidError,
            highestBid = uiState.highestBid.bidAmount,
            startBid = uiState.auction.startBid,
            onDismissRequest = {
                viewModel.onTriggerEvent(DetailViewEvent.HidePlaceBidBottomSheet)
            },
            onBidChanged = {
                viewModel.onTriggerEvent(DetailViewEvent.OnBidAmountChange(it))
            }
        ) {
            viewModel.onTriggerEvent(
                DetailViewEvent.SendBid(auctionId!!)
            )
        }
    }

    when {
        uiState.isLoading -> BaseLoading()
    }
}