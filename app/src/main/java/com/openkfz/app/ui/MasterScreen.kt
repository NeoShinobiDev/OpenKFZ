package com.openkfz.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MasterScreen() {


    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)

    ) {


        Text(
            text = "OpenKFZ Master",
            style = MaterialTheme.typography.headlineLarge
        )


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        Text(
            text = "Verwaltung"
        )


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        Button(
            onClick = {

            }
        ) {

            Text("Dokumente")

        }


        Button(
            onClick = {

            }
        ) {

            Text("Verbundene Geräte")

        }


        Button(
            onClick = {

            }
        ) {

            Text("Export")

        }

    }

}
