package com.arafat1419.collektr.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.arafat1419.collektr.presentation.ui.theme.Primary
import com.arafat1419.collektr.presentation.ui.theme.Secondary
import com.arafat1419.collektr.presentation.ui.theme.White
import com.arafat1419.collektr.presentation.utils.PresentationUtils.moneyFormat
import com.arafat1419.collektr.presentation.utils.PresentationUtils.toMoney

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceBidBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    error: String = "",
    bidAmount: Long = 0L,
    startBid: Long = 0L,
    highestBid: Long = 0L,
    onDismissRequest: () -> Unit,
    onBidChanged: (Long) -> Unit,
    onBidPlaced: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        dragHandle = {
            BottomSheetDefaults.DragHandle(
                color = White
            )
        },
        modifier = modifier,
        sheetState = sheetState,
        containerColor = Primary
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Your Bid Amount",
                modifier = Modifier.padding(start = 2.dp),
                style = MaterialTheme.typography.labelSmall,
            )

            AppOutlinedTextField(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                value = bidAmount.moneyFormat(),
                prefix = "RM",
                cursorAtEnd = true,
                onValueChange = { newValue ->
                    onBidChanged(newValue.toMoney())
                }
            )

            Text(
                text = if (highestBid > startBid) "Current Highest Bid: RM${highestBid.moneyFormat()}"
                else "Start Bid: RM${startBid.moneyFormat()}",
                modifier = Modifier
                    .padding(top = 8.dp, end = 2.dp)
                    .align(Alignment.End),
                style = MaterialTheme.typography.labelSmall,
            )

            if (error.isNotEmpty()) {
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = "Error",
                        modifier = Modifier.size(16.dp),
                        tint = White
                    )
                    Text(
                        text = error,
                        style = MaterialTheme.typography.labelLarge.copy(textDecoration = TextDecoration.Underline),
                        color = White
                    )
                }
            }

            Text(
                text = "Your bid amount will be held from your balance. If you are outbid, the hold will be released. If you win, the amount will be deducted from your balance.",
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Normal)
            )

            Row(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        onDismissRequest()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Secondary
                    ),
                    shape = RoundedCornerShape(8.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
                ) {
                    Text(
                        "Cancel",
                        style = MaterialTheme.typography.labelLarge,
                        color = White
                    )
                }

                SwipeToBid(
                    modifier = Modifier.weight(1F),
                    isBidSuccess = error.isEmpty(),
                    onBidPlaced = onBidPlaced
                )
            }
        }
    }
}