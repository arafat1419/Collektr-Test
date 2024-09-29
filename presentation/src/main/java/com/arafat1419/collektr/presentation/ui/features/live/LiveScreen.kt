package com.arafat1419.collektr.presentation.ui.features.live

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arafat1419.collektr.presentation.R
import com.arafat1419.collektr.presentation.ui.components.AuctionPopUp
import com.arafat1419.collektr.presentation.ui.components.BaseLoading
import com.arafat1419.collektr.presentation.ui.components.BottomMessageAndBid
import com.arafat1419.collektr.presentation.ui.components.CreatorProfile
import com.arafat1419.collektr.presentation.ui.components.List.MessageAndBidList
import com.arafat1419.collektr.presentation.ui.components.LiveCount
import com.arafat1419.collektr.presentation.ui.components.MessageAndBidItem
import com.arafat1419.collektr.presentation.ui.components.PlaceBidBottomSheet
import com.arafat1419.collektr.presentation.ui.components.TopAppBar
import com.arafat1419.collektr.presentation.ui.navigation.NavigationItem
import com.arafat1419.collektr.presentation.ui.theme.Primary
import com.arafat1419.collektr.presentation.ui.theme.Secondary
import com.arafat1419.collektr.presentation.ui.theme.White
import com.arafat1419.collektr.presentation.utils.PresentationUtils
import com.arafat1419.collektr.presentation.utils.PresentationUtils.moneyFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LiveViewModel = hiltViewModel(),
    auctionId: Int?,
) {
    val uiState by viewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(Unit) {
        if (auctionId == null) {
            navController.popBackStack()
            return@LaunchedEffect
        }

        viewModel.onTriggerEvent(LiveViewEvent.GetAuctionDetail(auctionId))
        viewModel.onTriggerEvent(LiveViewEvent.GetLiveAuctionCount(auctionId))

        viewModel.uiEvent.collect { event ->
            when (event) {
                LiveViewEvent.ShowPlaceBidBottomSheet -> sheetState.show()
                LiveViewEvent.HidePlaceBidBottomSheet -> sheetState.hide()
                else -> {}
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            modifier = modifier,
            navController = navController,
            navigationItem = NavigationItem.LiveAuction,
            actionIcon = if (uiState.auction.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
        ) {
            viewModel.onTriggerEvent(
                LiveViewEvent.SetFavoriteAuction(
                    auctionId!!,
                    !uiState.auction.isFavorite
                )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CreatorProfile(
                        modifier = Modifier
                            .background(Secondary.copy(alpha = 0.6F), RoundedCornerShape(16.dp))
                            .padding(end = 8.dp),
                        creator = uiState.auction.creator
                    )

                    Spacer(modifier = Modifier.weight(1F))

                    Text(
                        text = PresentationUtils.convertTimestampToDateTime(uiState.auction.auctionEnd),
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .background(
                                PresentationUtils.getBasicVerticalGradient(),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = White
                    )

                    LiveCount(
                        modifier = Modifier,
                        count = uiState.liveCount.toString()
                    )
                }

                AuctionPopUp(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
                    auction = uiState.auction
                )

                Spacer(Modifier.weight(1F))

                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 12.dp, end = 16.dp),
                ) {
                    MessageAndBidList(
                        modifier = Modifier
                            .height(((uiState.chatBids.size.coerceAtMost(3)) * 40).dp),
                        chatBids = uiState.chatBids
                    )
                    uiState.highestBid.let {
                        if (it.bidAmount > 0) {
                            MessageAndBidItem(
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .background(
                                        Primary.copy(alpha = 0.6F),
                                        RoundedCornerShape(4.dp)
                                    ),
                                icon = painterResource(R.drawable.outline_star_rate_24),
                                title = stringResource(R.string.highest_bid),
                                message = "${it.userName} (RM${it.bidAmount.moneyFormat()})"
                            )
                        }
                    }
                }

                BottomMessageAndBid(
                    message = uiState.chatMessage,
                    onMessageChange = {
                        viewModel.onTriggerEvent(
                            LiveViewEvent.OnChatMessageChange(
                                it
                            )
                        )
                    },
                    onSendClicked = {
                        viewModel.onTriggerEvent(
                            LiveViewEvent.SendMessage(
                                auctionId!!,
                                uiState.chatMessage
                            )
                        )
                    }
                ) {
                    viewModel.onTriggerEvent(LiveViewEvent.ShowPlaceBidBottomSheet)
                }
            }
        }
    }

    if (sheetState.isVisible) {
        PlaceBidBottomSheet(
            sheetState = sheetState,
            error = uiState.bidError,
            bidAmount = uiState.bidAmount,
            highestBid = uiState.highestBid.bidAmount,
            startBid = uiState.auction.startBid,
            onDismissRequest = {
                viewModel.onTriggerEvent(LiveViewEvent.HidePlaceBidBottomSheet)
            },
            onBidChanged = {
                viewModel.onTriggerEvent(LiveViewEvent.OnBidAmountChange(it))
            }
        ) {
            viewModel.onTriggerEvent(
                LiveViewEvent.SendBid(auctionId!!)
            )
        }
    }

    when {
        uiState.isLoading -> BaseLoading()
    }
}