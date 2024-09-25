package com.arafat1419.collektr.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arafat1419.collektr.presentation.ui.theme.Primary
import com.arafat1419.collektr.presentation.ui.theme.Secondary

object PresentationUtils {
    fun getBasicVerticalGradient() = Brush.verticalGradient(
        colors = listOf(
            Secondary,
            Primary
        )
    )

    @Composable
    fun GetScreenWidthInDp(): Dp {
        val configuration = LocalConfiguration.current
        val screenWidthPx = dpToPx(configuration.screenWidthDp.dp)

        val density = LocalDensity.current
        val screenWidthDp = with(density) { screenWidthPx.toDp() }

        return screenWidthDp
    }

    @Composable
    fun dpToPx(dpValue: Dp): Float {
        val density = LocalDensity.current
        return with(density) { dpValue.toPx() } // Converts Dp to pixels
    }
}