package com.arafat1419.collektr.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arafat1419.collektr.R
import com.arafat1419.collektr.presentation.ui.theme.Secondary
import com.arafat1419.collektr.presentation.ui.theme.White

@Composable
fun AuctionPopUp(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Secondary.copy(alpha = 0.6F), RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "https://cllktr.s3.ap-southeast-1.amazonaws.com/product/01J8JCRKXAPB2Z47B6GWVRS7KK-collektr.webp",
            contentDescription = stringResource(R.string.auction_item),
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
        )

        Column(
            modifier = Modifier.padding(start = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Moana - Undeterred Voyager",
                style = MaterialTheme.typography.labelMedium,
                color = White
            )
            Text(
                text = "“Moana - Undeterred Voyager” is a beautifully crafted, limited edition figure that celebrates the spirit of adventure and determination. This collectible captures Moana in a dynamic pose, standing tall on a base that resembles the deck of her ocean-faring canoe, her gaze fixed on the horizon. With intricate details, from the texture of her traditional Polynesian attire to the flowing waves beneath her, this piece embodies her unwavering courage and connection to the ocean. Perfect for fans and collectors alike, this figure serves as a reminder that no obstacle is too great when guided by purpose and heart.",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Normal),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = White
            )
        }
    }
}