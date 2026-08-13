package com.openkfz.app.ui.database

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.openkfz.data.Fahrzeug
import com.openkfz.data.OpenKfzDatabase
import kotlinx.coroutines.launch


@Composable
fun DatabaseScreen(){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val dao = remember {
        OpenKfzDatabase.getInstance(context).fahrzeugDao()
    }

    val fahrzeuge by dao.getAll().collectAsState(initial = emptyList())

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
            onClick = {}
        ){
            Text("Backup erstellen")
        }


        Button(
            onClick = {}
        ){
            Text("Export JSON")
        }


        Button(
            onClick = {}
        ){
            Text("Import Backup")
        }

    }

}
