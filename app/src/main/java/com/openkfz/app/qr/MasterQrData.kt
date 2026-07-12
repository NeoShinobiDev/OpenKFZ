package com.openkfz.app.qr

import android.content.Context
import java.net.Inet4Address
import java.net.NetworkInterface


object MasterQrData {


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

        } catch(e:Exception){

        }


        return "0.0.0.0"

    }



    fun create(context: Context): String {

        return """
        {
          "type":"openkfz-master",
          "host":"${getIp()}",
          "port":8080,
          "name":"OpenKFZ Master"
        }
        """.trimIndent()

    }

}
