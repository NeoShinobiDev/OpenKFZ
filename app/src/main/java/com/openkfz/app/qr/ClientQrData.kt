package com.openkfz.app.qr

import android.content.Context
import java.util.UUID


object ClientQrData {

    private const val PREFS = "openkfz"
    private const val KEY_CLIENT_ID = "client_id"

    fun getClientId(context: Context): String {

        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

        val existing = prefs.getString(KEY_CLIENT_ID, null)

        if (existing != null) {
            return existing
        }

        val generated = UUID.randomUUID().toString()

        prefs.edit().putString(KEY_CLIENT_ID, generated).apply()

        return generated

    }

    fun create(context: Context): String {

        return """
        {
          "type":"openkfz-client",
          "id":"${getClientId(context)}",
          "name":"OpenKFZ Client"
        }
        """.trimIndent()

    }

}
