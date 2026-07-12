package com.openkfz.app.ui.start

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun StartScreen(
    onMaster: () -> Unit,
    onClient: () -> Unit
){

    Column(

        modifier =
        Modifier
            .fillMaxSize()
            .padding(30.dp),

        verticalArrangement =
        Arrangement.Center

    ){

        Text(
            "OpenKFZ Einrichtung",
            style = MaterialTheme.typography.headlineLarge
        )


        Spacer(
            Modifier.height(40.dp)
        )


        Button(

            modifier =
            Modifier.fillMaxWidth(),

            onClick = onMaster

        ){

            Text(
                "🖥 Master einrichten"
            )

        }


        Spacer(
            Modifier.height(20.dp)
        )


        Button(

            modifier =
            Modifier.fillMaxWidth(),

            onClick = onClient

        ){

            Text(
                "📱 Client verbinden"
            )

        }

    }

}
