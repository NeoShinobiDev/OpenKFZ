package com.openkfz.app.ui.devices

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun DevicesScreen(){

    var lastScan by remember { mutableStateOf<String?>(null) }

    val scanLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->

        if (result.contents != null) {
            lastScan = result.contents
        }

    }

    Column {

        Text(
            text = "Geräte",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {

                val options = ScanOptions()
                options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                options.setPrompt("Client-QR-Code scannen")
                options.setBeepEnabled(false)

                scanLauncher.launch(options)

            }
        ) {
            Text("Client scannen")
        }

        Spacer(Modifier.height(16.dp))

        if (lastScan != null) {

            Text(
                text = "Zuletzt gescannt:",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(text = lastScan ?: "")

        } else {

            Text("Noch kein Gerät gescannt.")

        }

    }

}
