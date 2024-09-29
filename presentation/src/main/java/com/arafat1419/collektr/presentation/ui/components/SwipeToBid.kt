package com.arafat1419.collektr.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.arafat1419.collektr.presentation.ui.theme.Green
import com.arafat1419.collektr.presentation.ui.theme.Primary
import com.arafat1419.collektr.presentation.ui.theme.White
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun SwipeToBid(
    modifier: Modifier = Modifier,
    isBidSuccess: Boolean = false,
    onBidPlaced: () -> Unit
) {
    var currentState by remember { mutableStateOf(SwipeState.Default) }
    val coroutineScope = rememberCoroutineScope()

    val offset = remember { mutableFloatStateOf(0f) }

    BoxWithConstraints(
        modifier = modifier
    ) {
        val boxWidth = constraints.maxWidth.toFloat()
        val maxOffset = boxWidth - with(LocalDensity.current) {
            120.dp.toPx()
        }
        val isSwiped = offset.floatValue > (maxOffset * 4 / 5)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    when {
                        currentState == SwipeState.Default -> White
                        isBidSuccess -> Green
                        else -> Color.Red
                    },
                    shape = RoundedCornerShape(8.dp)
                )
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        offset.floatValue = (offset.floatValue + delta).coerceIn(0f, maxOffset)
                    },
                    onDragStopped = {
                        if (isSwiped) {
                            coroutineScope.launch {
                                currentState = SwipeState.BidPlaced
                                onBidPlaced()
                            }
                        } else {
                            offset.floatValue = 0f
                        }
                    }
                )
                .padding(4.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            when (currentState) {
                SwipeState.Default -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.offset { IntOffset(offset.floatValue.roundToInt(), 0) }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Primary, RoundedCornerShape(6.dp))
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null,
                                tint = White,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isSwiped) "Release" else "Slide to Bid",
                            style = MaterialTheme.typography.labelLarge,
                            color = Primary
                        )
                    }
                }

                SwipeState.BidPlaced -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = if (isBidSuccess) "Bid Placed" else "Bid Failed",
                            modifier = Modifier.weight(1F),
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center,
                            color = White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(White, RoundedCornerShape(6.dp))
                                .clickable {
                                    if (!isBidSuccess) {
                                        currentState = SwipeState.Default
                                        offset.floatValue = 0f
                                    }
                                }
                        ) {
                            Icon(
                                imageVector = if (isBidSuccess) Icons.Filled.Done else Icons.Filled.Refresh,
                                contentDescription = null,
                                tint = if (isBidSuccess) Green else Color.Red,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class SwipeState {
    Default,
    BidPlaced
}