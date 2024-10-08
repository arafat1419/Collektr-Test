package com.arafat1419.collektr.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arafat1419.collektr.domain.model.auction.AuctionCreator
import com.arafat1419.collektr.presentation.R
import com.arafat1419.collektr.presentation.ui.theme.White

@Composable
fun CreatorProfile(
    modifier: Modifier = Modifier,
    creator: AuctionCreator
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = creator.img,
            contentDescription = stringResource(R.string.creator_logo),
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
        )

        Text(
            text = creator.name,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.labelMedium,
            color = White
        )
    }
}