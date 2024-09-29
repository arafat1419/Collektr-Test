package com.arafat1419.collektr.presentation.ui.components.List

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arafat1419.collektr.domain.model.chatbid.ChatBid
import com.arafat1419.collektr.presentation.ui.components.MessageAndBidItem
import com.arafat1419.collektr.presentation.ui.theme.DarkGray
import com.arafat1419.collektr.presentation.ui.theme.Primary
import com.arafat1419.collektr.presentation.utils.PresentationUtils.moneyFormat

@Composable
fun MessageAndBidList(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    chatBids: List<ChatBid>,
) {
    LaunchedEffect(chatBids) {
        if (chatBids.isNotEmpty()) {
            listState.animateScrollToItem(chatBids.size)
        }
    }

    LazyColumn(
        modifier = modifier,
        state = listState,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = chatBids.sortedBy { it.createdAt },
            key = { "${it.id} - ${it.isBid} - ${it.auctionId}" }) {
            if (it.isBid) {
                MessageAndBidItem(
                    modifier = Modifier
                        .background(
                            Primary.copy(alpha = 0.6F),
                            RoundedCornerShape(4.dp)
                        ),
                    title = it.userName,
                    message = "BID RM${it.bidAmount.moneyFormat()}"
                )
            } else {
                MessageAndBidItem(
                    modifier = Modifier
                        .background(
                            DarkGray.copy(alpha = 0.6F),
                            RoundedCornerShape(4.dp)
                        ),
                    title = it.userName,
                    message = it.chatMessage
                )
            }
        }
    }
}