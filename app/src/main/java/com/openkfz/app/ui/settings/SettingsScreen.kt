package com.openkfz.app.ui.settings

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(){

    Card {

        Text(
            """
            ⚙ Einstellungen
            
            Server
            Netzwerk
            API
            Module
            """.trimIndent()
        )

    }

}
