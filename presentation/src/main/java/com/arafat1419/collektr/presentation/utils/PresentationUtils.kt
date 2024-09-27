package com.arafat1419.collektr.presentation.utils

import androidx.compose.ui.graphics.Brush
import com.arafat1419.collektr.presentation.ui.theme.Primary
import com.arafat1419.collektr.presentation.ui.theme.Secondary

object PresentationUtils {
    fun getBasicVerticalGradient() = Brush.verticalGradient(
        colors = listOf(
            Secondary,
            Primary
        )
    )

    fun Long.moneyFormat(): String {
        val formatted = StringBuilder()
        val reversedString = this.toString().reversed()

        for ((index, char) in reversedString.withIndex()) {
            if (index > 0 && index % 3 == 0) {
                formatted.append(".")
            }
            formatted.append(char)
        }

        formatted.reverse()

        return formatted.toString()
    }

    fun String.toMoney(): Long =
        if (this.isEmpty()) 0L else this.replace("[^\\d]".toRegex(), "").toLong()
}