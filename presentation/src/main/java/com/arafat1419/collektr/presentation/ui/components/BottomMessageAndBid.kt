package com.arafat1419.collektr.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arafat1419.collektr.presentation.R
import com.arafat1419.collektr.presentation.ui.theme.Black
import com.arafat1419.collektr.presentation.ui.theme.DarkGray
import com.arafat1419.collektr.presentation.ui.theme.White

@Composable
fun BottomMessageAndBid(
    modifier: Modifier = Modifier,
    message: String = "",
    onMessageChange: (String) -> Unit = {},
    onSendClicked: () -> Unit = {},
    onBidClicked: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        White.copy(alpha = 0.4F),
                        Black.copy(alpha = 0.4F)
                    )
                )
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppOutlinedTextField(
            modifier = Modifier.weight(1F),
            value = message,
            onValueChange = onMessageChange
        )

        IconButton(
            onClick = onSendClicked,
            enabled = message.isNotEmpty(),
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = White,
                disabledContentColor = DarkGray
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Send,
                contentDescription = stringResource(R.string.send_message),
            )
        }

        Button(
            onClick = onBidClicked,
            shape = RoundedCornerShape(8.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(0.dp)
        ) {
            Text(
                "Bid",
                style = MaterialTheme.typography.labelLarge,
                color = White
            )
        }
    }
}