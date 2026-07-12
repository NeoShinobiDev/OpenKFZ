package com.openkfz.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MasterScreen() {

    var selectedTab by remember {
        mutableIntStateOf(0)
    }

    Scaffold(

        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = {
                        Icon(
                            Icons.Default.Devices,
                            contentDescription = "Geräte"
                        )
                    },
                    label = {
                        Text("Geräte")
                    }
                )


                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = {
                        Icon(
                            Icons.Default.Folder,
                            contentDescription = "Dateien"
                        )
                    },
                    label = {
                        Text("Dateien")
                    }
                )


                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    },
                    label = {
                        Text("Settings")
                    }
                )

            }

        }

    ) { padding ->


        Surface(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)

        ) {


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


                when(selectedTab) {


                    0 -> {

                        Text(
                            text = "Verbundene Geräte"
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )


                        Text(
                            text = "Keine Geräte verbunden"
                        )

                    }


                    1 -> {

                        Text(
                            text = "Datei Browser"
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )


                        Button(
                            onClick = {}
                        ) {

                            Text("PDF öffnen")

                        }

                    }


                    2 -> {

                        Text(
                            text = "Master Einstellungen"
                        )


                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )


                        Text(
                            text = "Netzwerk\nBenutzer\nSystem"

                        )

                    }

                }

            }

        }

    }

}
