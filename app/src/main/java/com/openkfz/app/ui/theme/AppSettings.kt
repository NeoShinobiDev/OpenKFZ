package com.openkfz.app.ui.theme

import android.content.Context
import androidx.compose.runtime.mutableStateOf

object AppSettings {

    private const val PREFS = "openkfz"
    private const val KEY_DARK_MODE = "dark_mode"

    const val MODE_SYSTEM = "system"
    const val MODE_LIGHT = "light"
    const val MODE_DARK = "dark"

    private var initialized = false

    var darkModePreference = mutableStateOf(MODE_SYSTEM)
        private set

    fun init(context: Context) {

        if (initialized) return

        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        darkModePreference.value = prefs.getString(KEY_DARK_MODE, MODE_SYSTEM) ?: MODE_SYSTEM
        initialized = true

    }

    fun setDarkModePreference(context: Context, value: String) {

        darkModePreference.value = value

        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_DARK_MODE, value)
            .apply()

    }

}
