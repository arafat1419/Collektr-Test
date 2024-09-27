package com.arafat1419.collektr.presentation.ui.features.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.arafat1419.collektr.presentation.R
import com.arafat1419.collektr.presentation.ui.components.BottomMessageAndBid
import com.arafat1419.collektr.presentation.ui.components.CreatorProfile
import com.arafat1419.collektr.presentation.ui.components.List.MessageAndBidList
import com.arafat1419.collektr.presentation.ui.components.MessageAndBidItem
import com.arafat1419.collektr.presentation.ui.components.TopAppBar
import com.arafat1419.collektr.presentation.ui.navigation.NavigationItem
import com.arafat1419.collektr.presentation.ui.theme.LightWhite
import com.arafat1419.collektr.presentation.ui.theme.Primary
import com.arafat1419.collektr.presentation.ui.theme.White

@Composable
fun DetailScreen(
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
                navigationItem = NavigationItem.DetailAuction
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = "https://cllktr.s3.ap-southeast-1.amazonaws.com/product/01J8JCN84033XTGHVYTWBVJ57A-collektr.webp",
                    contentDescription = stringResource(R.string.auction_item),
                    modifier = Modifier
                        .padding(horizontal = 64.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.FillWidth
                )

                MessageAndBidItem(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                        .background(Primary.copy(alpha = 0.6F))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    icon = painterResource(R.drawable.outline_star_rate_24),
                    title = stringResource(R.string.highest_bid),
                    message = "Arafat Maku (RM150)"
                )

                Column(
                    modifier = Modifier.padding(start = 16.dp, top = 26.dp, end = 16.dp)
                ) {
                    Text(
                        text = "Scar - Fiery Usurper",
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
                                    .padding(top = 4.dp)
                            )
                        }

                        Column {
                            Text(
                                text = stringResource(R.string.ending_in),
                                style = MaterialTheme.typography.labelSmall,
                                color = LightWhite
                            )

                            Text(
                                text = "04:11:20",
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
                        text = "“Scar - Fiery Usurper” is a striking collectible figure that embodies the sinister allure of one of the most iconic villains in animation. Captured in a powerful stance, Scar is depicted with a menacing gaze and a wicked grin, exuding his treacherous ambition. ",
                        modifier = Modifier.padding(top = 8.dp),
                        style = MaterialTheme.typography.titleSmall,
                        color = White
                    )

                    Text(
                        text = stringResource(R.string.live_chat_bidding),
                        modifier = Modifier.padding(top = 16.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = LightWhite
                    )

                    MessageAndBidList(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 112.dp)
                            .fillMaxWidth()
                            .height(192.dp),
                        chatBids = chatBids
                    )
                }
            }
        }

        BottomMessageAndBid(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}