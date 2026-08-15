package com.openkfz.app.ui.devices

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.openkfz.app.qr.PairedDevice
import com.openkfz.app.qr.PairedDeviceStore
import org.json.JSONObject

@Composable
fun DevicesScreen(){

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        PairedDeviceStore.init(context)
    }

    var statusMessage by remember { mutableStateOf<String?>(null) }
    var deviceToRemove by remember { mutableStateOf<PairedDevice?>(null) }

    val scanLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->

        val content = result.contents

        if (content == null) {
            return@rememberLauncherForActivityResult
        }

        try {

            val json = JSONObject(content)

            if (json.optString("type") != "openkfz-client") {
                statusMessage = "Kein gültiger Client-QR-Code."
                return@rememberLauncherForActivityResult
            }

            val id = json.getString("id")
            val name = json.optString("name", "Unbenannter Client")

            PairedDeviceStore.addOrUpdate(context, PairedDevice(id, name))
            statusMessage = "\"$name\" verbunden."

        } catch (e: Exception) {
            statusMessage = "QR-Code konnte nicht gelesen werden."
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

        statusMessage?.let {
            Spacer(Modifier.height(8.dp))
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(Modifier.height(16.dp))

        val devices = PairedDeviceStore.devices.value

        if (devices.isEmpty()) {

            Text("Noch keine Geräte verbunden.")

        } else {

            Text(
                text = "${devices.size} verbunden(e) Gerät(e)",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(8.dp))

            LazyColumn {

                items(devices) { device ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {

                            Text(text = device.name, style = MaterialTheme.typography.bodyLarge)

                            Text(text = device.id, style = MaterialTheme.typography.bodySmall)

                        }

                        IconButton(
                            onClick = { deviceToRemove = device }
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Trennen")
                        }

                    }

                    HorizontalDivider()

                }

            }

        }

    }

    deviceToRemove?.let { device ->

        AlertDialog(
            onDismissRequest = { deviceToRemove = null },
            title = { Text("Gerät wirklich trennen?") },
            text = { Text("\"${device.name}\" wird getrennt und muss erneut per QR-Code gekoppelt werden.") },
            confirmButton = {
                TextButton(onClick = {
                    PairedDeviceStore.remove(context, device)
                    deviceToRemove = null
                }) {
                    Text("Trennen")
                }
            },
            dismissButton = {
                TextButton(onClick = { deviceToRemove = null }) {
                    Text("Abbrechen")
                }
            }
        )

    }

}
