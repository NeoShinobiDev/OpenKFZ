package com.openkfz.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.openkfz.app.ui.theme.OpenKfzTheme


class MasterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            OpenKfzTheme {

                MasterScreen()

            }

        }
    }
}
