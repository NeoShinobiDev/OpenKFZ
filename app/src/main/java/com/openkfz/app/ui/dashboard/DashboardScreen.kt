package com.openkfz.app.ui.dashboard

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun DashboardScreen(){

    Card {

        Text(
            text = """
            OpenKfz Dashboard
            
            🟢 System Online
            
            Geräte: 0
            Benutzer: 0
            Datenbank: OK
            """.trimIndent()
        )

    }

}
