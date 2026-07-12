package com.openkfz.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SetupScreen(
    onClientSelected: () -> Unit,
    onMasterSelected: () -> Unit
) {

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


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        Text(
            text = "Gerät einrichten",
            style = MaterialTheme.typography.titleMedium
        )


        Spacer(
            modifier = Modifier.height(32.dp)
        )


        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {


                Text(
                    text = "📱 Client Scanner",
                    style = MaterialTheme.typography.titleLarge
                )


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                Text(
                    text = "Nur Fahrzeugscheine scannen und Daten übertragen."
                )


                Spacer(
                    modifier = Modifier.height(16.dp)
                )


                Button(
                    modifier = Modifier.fillMaxWidth(),

                    onClick = {
                        onClientSelected()
                    }

                ) {

                    Text("Als Client einrichten")

                }

            }

        }


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Card(
            modifier = Modifier.fillMaxWidth()
        ) {


            Column(
                modifier = Modifier.padding(20.dp)
            ) {


                Text(
                    text = "🖥 Master Verwaltung",
                    style = MaterialTheme.typography.titleLarge
                )


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                Text(
                    text = "Dokumente verwalten, Geräte verbinden und exportieren."
                )


                Spacer(
                    modifier = Modifier.height(16.dp)
                )


                Button(
                    modifier = Modifier.fillMaxWidth(),

                    onClick = {
                        onMasterSelected()
                    }

                ) {

                    Text("Als Master einrichten")

                }

            }

        }

    }

}
