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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arafat1419.collektr.R
import com.arafat1419.collektr.presentation.PresentationUtils
import com.arafat1419.collektr.presentation.ui.components.AuctionPopUp
import com.arafat1419.collektr.presentation.ui.components.BottomMessageAndBid
import com.arafat1419.collektr.presentation.ui.components.CreatorProfile
import com.arafat1419.collektr.presentation.ui.components.List.MessageAndBidList
import com.arafat1419.collektr.presentation.ui.components.LiveCount
import com.arafat1419.collektr.presentation.ui.components.MessageAndBidItem
import com.arafat1419.collektr.presentation.ui.components.TopAppBar
import com.arafat1419.collektr.presentation.ui.navigation.NavigationItem
import com.arafat1419.collektr.presentation.ui.theme.Primary
import com.arafat1419.collektr.presentation.ui.theme.Secondary
import com.arafat1419.collektr.presentation.ui.theme.White

@Composable
fun LiveScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val chatBids = (0..10).toList()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            TopAppBar(
                modifier = modifier,
                navController = navController,
                navigationItem = NavigationItem.LiveAuction,
                actionIcon = Icons.Filled.FavoriteBorder
            ) {

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
                                .padding(end = 8.dp)
                        )

                        Spacer(modifier = Modifier.weight(1F))

                        Text(
                            text = "End in: 04:11:20",
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
                            count = "420"
                        )
                    }

                    AuctionPopUp(
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    )

                    Spacer(Modifier.weight(1F))

                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp, bottom = 12.dp),
                    ) {
                        MessageAndBidList(
                            modifier = Modifier.height(120.dp),
                            chatBids = chatBids
                        )
                        MessageAndBidItem(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .background(Primary.copy(alpha = 0.6F), RoundedCornerShape(4.dp)),
                            icon = painterResource(R.drawable.outline_star_rate_24),
                            title = stringResource(R.string.highest_bid),
                            message = "Arafat Maku (RM150)"
                        )
                    }

                    BottomMessageAndBid()
                }
            }
        }
    }
}