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
}