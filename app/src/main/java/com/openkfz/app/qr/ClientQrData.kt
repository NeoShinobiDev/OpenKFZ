package com.openkfz.app.qr

import android.content.Context
import java.net.Inet4Address
import java.net.NetworkInterface
import java.util.UUID


object ClientQrData {

    fun getIp(): String {
        try {
            val interfaces =
                NetworkInterface.getNetworkInterfaces()

            for (network in interfaces) {
                for (address in network.inetAddresses) {
                    if (!address.isLoopbackAddress &&
                        address is Inet4Address
                    ) {
                        return address.hostAddress ?: "0.0.0.0"
                    }
                }
            }
        } catch(e: Exception) {
        }

        return "0.0.0.0"
    }

    fun create(context: Context): String {
        val clientId = UUID.randomUUID().toString().take(8)

        return """
        {
          "type":"openkfz-client",
          "id":"$clientId",
          "host":"${getIp()}",
          "port":8081,
          "name":"OpenKFZ Client"
        }
        """.trimIndent()
    }
}
