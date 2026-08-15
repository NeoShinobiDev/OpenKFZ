package com.openkfz.app.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.openkfz.app.qr.PairedDeviceStore
import com.openkfz.data.OpenKfzDatabase

@Composable
fun DashboardScreen(){

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        PairedDeviceStore.init(context)
    }

    var dbStatus by remember { mutableStateOf("Prüfe…") }

    LaunchedEffect(Unit) {
        dbStatus = try {
            OpenKfzDatabase.getInstance(context).fahrzeugDao().count()
            "OK"
        } catch (e: Exception) {
            "Fehler"
        }
    }

    val deviceCount = PairedDeviceStore.devices.value.size

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = "OpenKFZ Dashboard",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "🟢 System Online",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )

            DashboardStat("Geräte", deviceCount.toString())
            DashboardStat("Datenbank", dbStatus)

        }

    }

}

@Composable
private fun DashboardStat(label: String, value: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        Text(text = value, style = MaterialTheme.typography.bodyMedium)

    }

}
