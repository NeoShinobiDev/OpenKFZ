package com.openkfz.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ClientScreen() {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = "OpenKFZ Scanner",
            style = MaterialTheme.typography.headlineLarge
        )


        Spacer(
            modifier = Modifier.height(32.dp)
        )


        Text(
            text = "Fahrzeugschein vor Kamera halten"
        )


        Spacer(
            modifier = Modifier.height(32.dp)
        )


        Button(
            onClick = {
                // später Kamera starten
            }
        ) {

            Text("Scan starten")

        }

    }

}
