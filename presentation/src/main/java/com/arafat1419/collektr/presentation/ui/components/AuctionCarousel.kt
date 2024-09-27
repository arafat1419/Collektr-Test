package com.arafat1419.collektr.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arafat1419.collektr.presentation.R
import com.arafat1419.collektr.presentation.ui.features.home.AuctionItem
import com.arafat1419.collektr.presentation.ui.theme.Secondary
import com.arafat1419.collektr.presentation.ui.theme.White
import com.arafat1419.collektr.presentation.utils.PresentationUtils
import com.arafat1419.collektr.presentation.utils.PresentationUtils.moneyFormat

@Composable
fun AuctionCarousel(
    modifier: Modifier = Modifier,
    auctions: List<AuctionItem>,
    onItemClick: (AuctionItem) -> Unit
) {
    val pagerState = rememberPagerState { auctions.size }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
        HorizontalPager(
            state = pagerState,
            modifier = modifier.fillMaxWidth(),
            pageSize = PageSize.Fill,
            pageSpacing = 16.dp,
            contentPadding = PaddingValues(horizontal = 54.dp)
        ) { page ->
            val item = auctions[page]
            val isActive = pagerState.currentPage == page

            Card(
                modifier = Modifier
                    .aspectRatio(252f / if (isActive) 400f else 380f)
                    .clickable {
                        onItemClick(item)
                    },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AsyncImage(
                        model = item.imageUrl,
                        contentDescription = item.title,
                        modifier = Modifier
                            .aspectRatio(252f / if (isActive) 380f else 350f)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )

                    if (item.isLive) {
                        Text(
                            text = stringResource(R.string.live),
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(12.dp)
                                .background(
                                    brush = PresentationUtils.getBasicVerticalGradient(),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(vertical = 4.dp, horizontal = 8.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = White
                        )
                    }

                    if (isActive) {
                        Card(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(horizontal = 20.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Secondary,
                                contentColor = White,
                            ),
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(vertical = 8.dp, horizontal = 24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = FontWeight.Normal
                                    ),
                                )
                                Text(
                                    text = "${item.auctionEnd} | ${
                                        stringResource(
                                            R.string.rm_format,
                                            item.highestBid.moneyFormat()
                                        )
                                    }",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        }
        DotsIndicator(
            modifier = Modifier.padding(top = 32.dp),
            totalDots = auctions.size,
            selectedIndex = pagerState.currentPage,
            selectedColor = Secondary,
            unSelectedColor = White
        )

        Spacer(Modifier.weight(1f))
    }
}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}