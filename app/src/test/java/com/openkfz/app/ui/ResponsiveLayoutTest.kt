package com.openkfz.app.ui

import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Test

class ResponsiveLayoutTest {

    @Test
    fun compactWidthUsesCompactLayout() {
        assertEquals(ScreenMode.Compact, determineScreenMode(360.dp))
    }

    @Test
    fun wideWidthUsesExpandedLayout() {
        assertEquals(ScreenMode.Expanded, determineScreenMode(840.dp))
    }
}
