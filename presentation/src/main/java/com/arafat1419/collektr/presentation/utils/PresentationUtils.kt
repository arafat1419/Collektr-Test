package com.arafat1419.collektr.presentation.utils

import androidx.compose.ui.graphics.Brush
import com.arafat1419.collektr.presentation.ui.theme.Primary
import com.arafat1419.collektr.presentation.ui.theme.Secondary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

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


    fun convertTimestampToDateTime(
        timestamp: Long,
        pattern: String = "yyyy-MM-dd HH:mm:ss"
    ): String {
        val date = Date(timestamp * 1000)

        val formatter = SimpleDateFormat(pattern, Locale.getDefault()).apply {
            timeZone = TimeZone.getDefault()
        }

        return formatter.format(date)
    }
}