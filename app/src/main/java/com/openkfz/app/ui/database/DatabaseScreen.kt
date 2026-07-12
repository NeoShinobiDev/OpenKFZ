package com.openkfz.app.ui.database

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable


@Composable
fun DatabaseScreen(){

    Column {

        Text(
            text = "🗄 Datenbank Verwaltung"
        )


        Button(
            onClick = {}
        ){
            Text("Backup erstellen")
        }


        Button(
            onClick = {}
        ){
            Text("Export JSON")
        }


        Button(
            onClick = {}
        ){
            Text("Import Backup")
        }

    }

}
