package com.openkfz.app.ui.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.PhoneAndroid
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
            .background(MaterialTheme.colorScheme.background)
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
            Modifier
                .fillMaxWidth()
                .height(56.dp),

            onClick = onMaster

        ){

            Icon(
                imageVector = Icons.Default.Computer,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Spacer(Modifier.width(12.dp))

            Text(
                text = "Master einrichten",
                style = MaterialTheme.typography.labelLarge
            )

        }


        Spacer(
            Modifier.height(16.dp)
        )


        Button(

            modifier =
            Modifier
                .fillMaxWidth()
                .height(56.dp),

            onClick = onClient

        ){

            Icon(
                imageVector = Icons.Default.PhoneAndroid,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Spacer(Modifier.width(12.dp))

            Text(
                text = "Client verbinden",
                style = MaterialTheme.typography.labelLarge
            )

        }

    }

}
