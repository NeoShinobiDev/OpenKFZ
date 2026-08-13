package com.openkfz.app.ui

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ScreenMode {
    Compact,
    Expanded
}

fun determineScreenMode(width: Dp): ScreenMode {
    return if (width < 600.dp) ScreenMode.Compact else ScreenMode.Expanded
}
