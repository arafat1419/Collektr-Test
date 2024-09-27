package com.arafat1419.collektr.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arafat1419.collektr.presentation.R
import com.arafat1419.collektr.presentation.ui.theme.White

@Composable
fun MessageAndBidItem(
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    title: String,
    message: String
) {
    Row(
        modifier = modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (icon != null) {
            Icon(
                painter = icon,
                contentDescription = stringResource(R.string.highest_bid),
                modifier = Modifier.size(16.dp),
                tint = White
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = "$title: ",
            style = MaterialTheme.typography.labelMedium,
            color = White,
        )

        Text(
            text = message,
            style = MaterialTheme.typography.bodySmall,
            color = White
        )
    }
}