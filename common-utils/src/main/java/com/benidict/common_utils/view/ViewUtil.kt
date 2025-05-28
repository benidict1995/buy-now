package com.benidict.common_utils.view

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun transparentLinearBrush(colors: List<Color>, alphaPercent: Float = 1f): Brush {
    val colorUpdate = colors.toMutableList()
    colorUpdate[0] = colorUpdate[0].copy(alpha = alphaPercent)
    return Brush.linearGradient(colors = colorUpdate.toList())
}

fun transparentVerticalBrush(colors: List<Color>, alphaPercent: Float = 1f): Brush {
    val colorUpdate = colors.toMutableList()
    colorUpdate[0] = colorUpdate[0].copy(alpha = alphaPercent)
    return Brush.verticalGradient(colors = colorUpdate.toList())
}