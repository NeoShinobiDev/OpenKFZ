package com.openkfz.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun SetupScreen(
    onClientSelected: () -> Unit,
    onMasterSelected: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val isWideScreen = configuration.screenWidthDp >= 600

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "OpenKFZ",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Gerät einrichten",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (isWideScreen) {
            Row(
                modifier = Modifier.widthIn(max = 900.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                SetupOptionCard(
                    title = "📱 Client Scanner",
                    description = "Nur Fahrzeugscheine scannen und Daten übertragen.",
                    buttonText = "Als Client einrichten",
                    onClick = onClientSelected,
                    modifier = Modifier.weight(1f)
                )

                SetupOptionCard(
                    title = "🖥 Master Verwaltung",
                    description = "Dokumente verwalten, Geräte verbinden und exportieren.",
                    buttonText = "Als Master einrichten",
                    onClick = onMasterSelected,
                    modifier = Modifier.weight(1f)
                )
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                SetupOptionCard(
                    title = "📱 Client Scanner",
                    description = "Nur Fahrzeugscheine scannen und Daten übertragen.",
                    buttonText = "Als Client einrichten",
                    onClick = onClientSelected
                )

                SetupOptionCard(
                    title = "🖥 Master Verwaltung",
                    description = "Dokumente verwalten, Geräte verbinden und exportieren.",
                    buttonText = "Als Master einrichten",
                    onClick = onMasterSelected
                )
            }
        }
    }
}

@Composable
private fun SetupOptionCard(
    title: String,
    description: String,
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = description)

            Spacer(modifier = Modifier.height(16.dp))

            Button(modifier = Modifier.fillMaxWidth(), onClick = onClick) {
                Text(buttonText)
            }
        }
    }
}
