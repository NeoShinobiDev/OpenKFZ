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

    var selectedTab by remember { mutableIntStateOf(0) }


    Scaffold(

        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = {
                        Icon(
                            Icons.Default.Devices,
                            null
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
                            null
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
                            null
                        )
                    },
                    label = {
                        Text("Settings")
                    }
                )

            }

        }

    ){ padding ->


        Column(

            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(20.dp)

        ){


            Text(
                text = "OpenKFZ Master",
                style = MaterialTheme.typography.headlineLarge
            )


            Spacer(
                Modifier.height(20.dp)
            )


            when(selectedTab){


                0 -> DeviceDashboard()


                1 -> FileDashboard()


                2 -> SettingsDashboard()


            }


        }

    }

}



@Composable
fun DeviceDashboard(){

    Card(

        modifier = Modifier
            .fillMaxWidth()

    ){

        Column(
            Modifier.padding(20.dp)
        ){

            Icon(
                Icons.Default.DirectionsCar,
                null
            )

            Text(
                "Verbundene Fahrzeuge"
            )


            Text(
                "Keine Geräte verbunden"
            )

        }

    }

}



@Composable
fun FileDashboard(){

    Column{


        Card(
            Modifier.fillMaxWidth()
        ){

            Row(
                Modifier.padding(20.dp)
            ){

                Icon(
                    Icons.Default.Folder,
                    null
                )

                Spacer(
                    Modifier.width(15.dp)
                )

                Text(
                    "Dateibrowser"
                )

            }

        }


        Spacer(
            Modifier.height(15.dp)
        )


        Card(
            Modifier.fillMaxWidth()
        ){

            Row(
                Modifier.padding(20.dp)
            ){

                Icon(
                    Icons.Default.PictureAsPdf,
                    null
                )

                Spacer(
                    Modifier.width(15.dp)
                )

                Text(
                    "PDF Dokumente"
                )

            }

        }

    }

}



@Composable
fun SettingsDashboard(){

    Card(

        Modifier.fillMaxWidth()

    ){

        Column(
            Modifier.padding(20.dp)
        ){

            Icon(
                Icons.Default.Settings,
                null
            )


            Text(
                "Master Einstellungen"
            )


        }

    }

}
