package com.openkfz.app.ui.admin

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun AdminScreen(){

    Card {

        Text(
            """
            👤 Admin Panel
            
            Benutzer
            Rollen
            Rechte
            Sicherheit
            """.trimIndent()
        )

    }

}
