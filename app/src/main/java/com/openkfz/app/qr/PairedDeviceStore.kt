package com.openkfz.app.qr

import android.content.Context
import androidx.compose.runtime.mutableStateOf

data class PairedDevice(val id: String, val name: String)

object PairedDeviceStore {

    private const val PREFS = "openkfz"
    private const val KEY_DEVICES = "paired_devices"

    private var initialized = false

    val devices = mutableStateOf<List<PairedDevice>>(emptyList())

    fun init(context: Context) {

        if (initialized) return

        val raw = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getStringSet(KEY_DEVICES, emptySet()) ?: emptySet()

        devices.value = raw.mapNotNull { entry ->
            val parts = entry.split("|", limit = 2)
            if (parts.size == 2) PairedDevice(parts[0], parts[1]) else null
        }

        initialized = true

    }

    fun addOrUpdate(context: Context, device: PairedDevice) {

        val updated = devices.value.filterNot { it.id == device.id } + device
        persist(context, updated)

    }

    fun remove(context: Context, device: PairedDevice) {

        val updated = devices.value.filterNot { it.id == device.id }
        persist(context, updated)

    }

    private fun persist(context: Context, updated: List<PairedDevice>) {

        devices.value = updated

        val serialized = updated.map { "${it.id}|${it.name}" }.toSet()

        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putStringSet(KEY_DEVICES, serialized)
            .apply()

    }

}
