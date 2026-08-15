package com.openkfz.app.ui.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.openkfz.app.qr.PairedDeviceStore
import com.openkfz.data.OpenKfzDatabase
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun AdminScreen(){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val dao = remember { OpenKfzDatabase.getInstance(context).fahrzeugDao() }

    fun documentsDir() = File(context.getExternalFilesDir(null), "documents")

    var vehicleCount by remember { mutableIntStateOf(0) }
    var fileCount by remember { mutableIntStateOf(0) }

    var showResetDbDialog by remember { mutableStateOf(false) }
    var showDeleteFilesDialog by remember { mutableStateOf(false) }
    var showRemoveDevicesDialog by remember { mutableStateOf(false) }

    suspend fun refreshCounts() {
        vehicleCount = dao.count()
        fileCount = documentsDir().listFiles()?.size ?: 0
    }

    LaunchedEffect(Unit) {
        PairedDeviceStore.init(context)
        refreshCounts()
    }

    val deviceCount = PairedDeviceStore.devices.value.size

    Column {

        Text(
            text = "Admin",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Systemstatus",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(8.dp))
        Text(text = "Fahrzeuge in der Datenbank: $vehicleCount")
        Text(text = "Gespeicherte Dateien: $fileCount")
        Text(text = "Verbundene Geräte: $deviceCount")

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Gefahrenzone",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(8.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            onClick = { showResetDbDialog = true }
        ) {
            Text("Datenbank leeren")
        }

        Spacer(Modifier.height(12.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            onClick = { showDeleteFilesDialog = true }
        ) {
            Text("Alle Dateien löschen")
        }

        Spacer(Modifier.height(12.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            onClick = { showRemoveDevicesDialog = true }
        ) {
            Text("Alle Geräte trennen")
        }

    }

    if (showResetDbDialog) {

        AlertDialog(
            onDismissRequest = { showResetDbDialog = false },
            title = { Text("Datenbank wirklich leeren?") },
            text = { Text("Alle $vehicleCount gespeicherten Fahrzeuge werden unwiderruflich gelöscht.") },
            confirmButton = {
                TextButton(onClick = {
                    scope.launch {
                        dao.deleteAll()
                        refreshCounts()
                        showResetDbDialog = false
                    }
                }) {
                    Text("Löschen")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDbDialog = false }) {
                    Text("Abbrechen")
                }
            }
        )

    }

    if (showDeleteFilesDialog) {

        AlertDialog(
            onDismissRequest = { showDeleteFilesDialog = false },
            title = { Text("Alle Dateien wirklich löschen?") },
            text = { Text("Alle $fileCount gespeicherten Dateien werden unwiderruflich gelöscht.") },
            confirmButton = {
                TextButton(onClick = {
                    documentsDir().listFiles()?.forEach { it.delete() }
                    scope.launch { refreshCounts() }
                    showDeleteFilesDialog = false
                }) {
                    Text("Löschen")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteFilesDialog = false }) {
                    Text("Abbrechen")
                }
            }
        )

    }

    if (showRemoveDevicesDialog) {

        AlertDialog(
            onDismissRequest = { showRemoveDevicesDialog = false },
            title = { Text("Alle Geräte wirklich trennen?") },
            text = { Text("Alle $deviceCount verbundenen Geräte werden entfernt. Sie müssen erneut per QR-Code gekoppelt werden.") },
            confirmButton = {
                TextButton(onClick = {
                    PairedDeviceStore.clearAll(context)
                    showRemoveDevicesDialog = false
                }) {
                    Text("Trennen")
                }
            },
            dismissButton = {
                TextButton(onClick = { showRemoveDevicesDialog = false }) {
                    Text("Abbrechen")
                }
            }
        )

    }

}
