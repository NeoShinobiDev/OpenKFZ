package com.openkfz.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.openkfz.app.ui.ClientScreen
import com.openkfz.app.ui.MasterScreen
import com.openkfz.app.ui.SetupScreen


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


    var screen by remember {
        mutableStateOf("setup")
    }


    when(screen) {


        "setup" -> {

            SetupScreen(

                onClientSelected = {

                    screen = "client"

                },

                onMasterSelected = {

                    screen = "master"

                }

            )

        }


        "client" -> {

            ClientScreen()

        }


        "master" -> {

            MasterScreen()

        }


    }

}
