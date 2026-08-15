package com.openkfz.app.ui.database

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.openkfz.app.files.documentsDir
import com.openkfz.data.Fahrzeug
import com.openkfz.data.OpenKfzDatabase
import com.openkfz.data.parseFahrzeugeJson
import com.openkfz.data.toJson
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun DatabaseScreen(){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val dao = remember {
        OpenKfzDatabase.getInstance(context).fahrzeugDao()
    }

    val fahrzeuge by dao.getAll().collectAsState(initial = emptyList())

    var statusMessage by remember { mutableStateOf<String?>(null) }

    fun timestamp() = SimpleDateFormat("yyyy-MM-dd_HHmmss", Locale.GERMANY).format(Date())

    val importLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->

        if (uri == null) return@rememberLauncherForActivityResult

        scope.launch {

            try {

                val text = context.contentResolver.openInputStream(uri)
                    ?.bufferedReader()
                    ?.use { it.readText() }
                    ?: throw IllegalStateException("Datei leer")

                val imported = parseFahrzeugeJson(text)

                imported.forEach { dao.insert(it) }

                statusMessage = "${imported.size} Fahrzeug(e) importiert."

            } catch (e: Exception) {

                statusMessage = "Import fehlgeschlagen: ${e.message}"

            }

        }

    }

    Column {

        Text(
            text = "🗄 Datenbank Verwaltung"
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Gespeicherte Fahrzeuge: ${fahrzeuge.size}"
        )

        Spacer(Modifier.height(8.dp))

        if (fahrzeuge.isEmpty()) {

            Text(text = "Noch keine Fahrzeuge gespeichert.")

        } else {

            LazyColumn {

                items(fahrzeuge) { fahrzeug ->

                    Text(
                        text = "${fahrzeug.kennzeichen} — ${fahrzeug.hersteller} ${fahrzeug.modell} (${fahrzeug.baujahr})"
                    )

                }

            }

        }

        statusMessage?.let {
            Spacer(Modifier.height(8.dp))
            Text(text = it, style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {

                scope.launch {

                    dao.insert(
                        Fahrzeug(
                            halter = "Test Halter",
                            kennzeichen = "LB-OK ${(100..999).random()}",
                            fin = "TESTFIN000000000",
                            hersteller = "Testhersteller",
                            modell = "Testmodell",
                            baujahr = 2026
                        )
                    )

                }

            }
        ){
            Text("Testfahrzeug hinzufügen")
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {

                val file = File(documentsDir(context), "backup_${timestamp()}.json")
                file.writeText(fahrzeuge.toJson())
                statusMessage = "Backup gespeichert: ${file.name}"

            }
        ){
            Text("Backup erstellen")
        }


        Button(
            onClick = {

                val file = File(documentsDir(context), "export_${timestamp()}.json")
                file.writeText(fahrzeuge.toJson())
                statusMessage = "Export gespeichert: ${file.name}"

            }
        ){
            Text("Export JSON")
        }


        Button(
            onClick = {
                importLauncher.launch("application/json")
            }
        ){
            Text("Import Backup")
        }

    }

}
