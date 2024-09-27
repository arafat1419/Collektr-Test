package com.arafat1419.collektr.presentation.ui.components.List

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arafat1419.collektr.presentation.ui.components.MessageAndBidItem
import com.arafat1419.collektr.presentation.ui.theme.DarkGray
import com.arafat1419.collektr.presentation.ui.theme.Primary
import kotlinx.coroutines.launch

@Composable
fun MessageAndBidList(
    modifier: Modifier = Modifier,
    chatBids: List<Int>,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            listState.scrollToItem(chatBids.size - 1)
        }
    }

    LazyColumn(
        modifier = modifier,
        state = listState,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(chatBids) { index, item ->
            if (index % 2 == 0) {
                MessageAndBidItem(
                    modifier = Modifier
                        .background(
                            Primary.copy(alpha = 0.6F),
                            RoundedCornerShape(4.dp)
                        ),
                    title = "Arafat Maku",
                    message = "BID RM150"
                )
            } else {
                MessageAndBidItem(
                    modifier = Modifier
                        .background(
                            DarkGray.copy(alpha = 0.6F),
                            RoundedCornerShape(4.dp)
                        ),
                    title = "Arafat Maku",
                    message = "Amazing Stuff!!"
                )
            }
        }
    }
}