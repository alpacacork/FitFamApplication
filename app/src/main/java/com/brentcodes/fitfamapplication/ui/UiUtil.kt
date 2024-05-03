package com.brentcodes.fitfamapplication.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

fun lightenColor(color: Color, fraction: Float): Color {
    val hsv = FloatArray(3)
    ColorUtils.colorToHSL(color.toArgb(), hsv)
    hsv[2] += (1f - hsv[2]) * fraction
    return Color(ColorUtils.HSLToColor(hsv))
}

fun modifyColor(color: Color, fraction: Float, alpha: Float = 1f): Color {
    val hsv = FloatArray(3)
    ColorUtils.colorToHSL(color.toArgb(), hsv)
    if (fraction < 0) hsv[2] *= 1 - fraction else hsv[2] += (1f - hsv[2]) * fraction
    return Color(ColorUtils.HSLToColor(hsv)).copy(alpha = alpha)
}

fun darkenColor(color: Color, fraction: Float): Color {
    val hsv = FloatArray(3)
    ColorUtils.colorToHSL(color.toArgb(), hsv)
    hsv[2] *= 1 - fraction
    return Color(ColorUtils.HSLToColor(hsv))
}