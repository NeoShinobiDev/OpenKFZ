@file:OptIn(ExperimentalMaterial3Api::class)

package com.openkfz.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OpenKFZApp()
        }
    }
}


@Composable
fun OpenKFZApp() {

    var selectedItem by remember {
        mutableIntStateOf(0)
    }

    val screens = listOf(
        "Dashboard",
        "Fahrzeuge",
        "Dokumente",
        "Einstellungen"
    )

    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.DirectionsCar,
        Icons.Default.Description,
        Icons.Default.Settings
    )


    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text("OpenKFZ")
                }
            )

        },


        bottomBar = {

            NavigationBar {

                screens.forEachIndexed { index, name ->

                    NavigationBarItem(

                        selected = selectedItem == index,

                        onClick = {
                            selectedItem = index
                        },

                        icon = {

                            Icon(
                                imageVector = icons[index],
                                contentDescription = name
                            )

                        },

                        label = {
                            Text(name)
                        }

                    )

                }

            }

        }


    ) { padding ->


        Box(

            modifier = Modifier
                .padding(padding)
                .fillMaxSize()

        ) {


            when(selectedItem) {

                0 -> DashboardScreen()

                1 -> SimpleScreen(
                    "Fahrzeuge"
                )

                2 -> SimpleScreen(
                    "Dokumente"
                )

                3 -> SimpleScreen(
                    "Einstellungen"
                )

            }

        }

    }

}



@Composable
fun DashboardScreen() {

    Column(

        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()

    ) {


        Text(

            text = "Willkommen bei OpenKFZ",

            style = MaterialTheme.typography.headlineSmall

        )


        Spacer(

            modifier = Modifier.height(24.dp)

        )


        DashboardCard(

            title = "🚗 Fahrzeuge",

            text = "Noch keine Fahrzeuge angelegt"

        )


        Spacer(

            modifier = Modifier.height(16.dp)

        )


        DashboardCard(

            title = "🔧 Wartungen",

            text = "Keine offenen Wartungen"

        )


        Spacer(

            modifier = Modifier.height(16.dp)

        )


        DashboardCard(

            title = "📄 Dokumente",

            text = "Keine Dokumente vorhanden"

        )

    }

}



@Composable
fun DashboardCard(

    title: String,

    text: String

) {

    Card(

        modifier = Modifier
            .fillMaxWidth()

    ) {


        Column(

            modifier = Modifier
                .padding(16.dp)

        ) {


            Text(

                text = title,

                style = MaterialTheme.typography.titleMedium

            )


            Spacer(

                modifier = Modifier.height(8.dp)

            )


            Text(

                text = text

            )

        }

    }

}



@Composable
fun SimpleScreen(

    name: String

) {


    Column(

        modifier = Modifier
            .padding(16.dp)

    ) {


        Text(

            text = name,

            style = MaterialTheme.typography.headlineMedium

        )


        Spacer(

            modifier = Modifier.height(16.dp)

        )


        Text(

            text = "Bereich wird entwickelt..."

        )

    }

}
