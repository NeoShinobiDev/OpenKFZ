package com.openkfz.app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = OpenKfzBlue,
    secondary = OpenKfzTeal,
    background = OpenKfzBackgroundLight,
    surface = OpenKfzSurfaceLight
)

private val DarkColors = darkColorScheme(
    primary = OpenKfzTeal,
    secondary = OpenKfzBlueDark,
    background = OpenKfzBackgroundDark,
    surface = OpenKfzSurfaceDark
)

@Composable
fun OpenKfzTheme(
    darkTheme: Boolean = run {
        val context = LocalContext.current
        AppSettings.init(context)
        when (AppSettings.darkModePreference.value) {
            AppSettings.MODE_DARK -> true
            AppSettings.MODE_LIGHT -> false
            else -> isSystemInDarkTheme()
        }
    },
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
