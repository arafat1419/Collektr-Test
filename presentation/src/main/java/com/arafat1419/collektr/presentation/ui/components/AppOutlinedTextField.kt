package com.arafat1419.collektr.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.arafat1419.collektr.presentation.ui.theme.LightWhite
import com.arafat1419.collektr.presentation.ui.theme.White

@Composable
fun AppOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    prefix: String = "",
    cursorAtEnd: Boolean = false,
    onValueChange: (String) -> Unit = {}
) {
    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(value.length),
            )
        )
    }

    BasicTextField(
        value = textFieldValue.copy(
            text = value,
            selection = if (cursorAtEnd) TextRange(value.length) else textFieldValue.selection
        ),
        onValueChange = {
            val newValue = it.text
            textFieldValue = it.copy(
                text = newValue,
                selection = if (cursorAtEnd) TextRange(newValue.length) else it.selection
            )
            onValueChange(newValue)
        },
        modifier = modifier
            .height(40.dp)
            .border(1.dp, White, RoundedCornerShape(8.dp)),
        singleLine = true,
        textStyle = MaterialTheme.typography.labelSmall.copy(
            color = White
        ),
        cursorBrush = SolidColor(White),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (prefix.isNotEmpty()) {
                    Text(
                        text = prefix,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = White
                        )
                    )
                }

                Box(modifier = Modifier.fillMaxWidth()) {
                    if (value.isEmpty()) {
                        Text(
                            text = "Type a message...",
                            style = MaterialTheme.typography.labelSmall,
                            color = LightWhite
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}