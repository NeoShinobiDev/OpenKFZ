package com.openkfz.app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.QrCode



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.openkfz.app.ui.dashboard.DashboardScreen
import com.openkfz.app.ui.settings.SettingsScreen
import com.openkfz.app.ui.devices.DevicesScreen


import com.openkfz.app.ui.qr.QrScreen
import com.openkfz.app.ui.admin.AdminScreen
import com.openkfz.app.ui.files.FileManagerScreen
import com.openkfz.app.ui.database.DatabaseScreen


@Composable
fun MasterScreen(){

    var page by remember {
        mutableStateOf(0)
    }


    Scaffold(

        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = page == 0,
                    onClick = { page = 0 },
                    icon = {
                        Icon(Icons.Default.Home,null)
                    },
                    label = {
                        Text("Dashboard")
                    }
                )


                NavigationBarItem(
                    selected = page == 1,
                    onClick = { page = 1 },
                    icon = {
                        Icon(Icons.Default.PhoneAndroid,null)
                    },
                    label = {
                        Text("Geräte")
                    }
                )


                NavigationBarItem(
                    selected = page == 2,
                    onClick = { page = 2 },
                    icon = {
                        Icon(Icons.Default.Build,null)
                    },
                    label = {
                        Text("Einstellungen")
                    }
                )


                NavigationBarItem(
                    selected = page == 3,
                    onClick = { page = 3 },
                    icon = {
                        Icon(Icons.Default.Folder,null)
                    },
                    label = {
                        Text("Dateien")
                    }
                )


                NavigationBarItem(
                    selected = page == 4,
                    onClick = { page = 4 },
                    icon = {
                        Icon(Icons.Default.Storage,null)
                    },
                    label = {
                        Text("Datenbank")
                    }
                )


                NavigationBarItem(
                    selected = page == 5,
                    onClick = { page = 5 },
                    icon = {
                        Icon(Icons.Default.AdminPanelSettings,null)
                    },
                    label = {
                        Text("Admin")
                    }
                )


                NavigationBarItem(
                    selected = page == 6,
                    onClick = { page = 6 },
                    icon = {
                        Icon(Icons.Default.QrCode,null)
                    },
                    label = {
                        Text("QR")
                    }
                )

            }

        }

    ){ padding ->


        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(20.dp)
        ){

            when(page){

                0 -> DashboardScreen()

                1 -> DevicesScreen()

                2 -> SettingsScreen()

                3 -> FileManagerScreen()

                4 -> DatabaseScreen()

                5 -> AdminScreen()

                6 -> QrScreen()

            }

        }

    }

}