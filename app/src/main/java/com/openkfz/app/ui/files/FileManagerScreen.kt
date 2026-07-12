package com.openkfz.app.ui.files

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun FileManagerScreen(){

    Card {

        Text(
            """
            📁 Dateimanager
            
            /vehicles
            /backup
            /logs
            /config
            """.trimIndent()
        )

    }

}
