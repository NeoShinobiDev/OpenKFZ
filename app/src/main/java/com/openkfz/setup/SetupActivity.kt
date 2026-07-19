package com.openkfz.setup

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.openkfz.app.ui.MasterActivity
import com.openkfz.client.ClientActivity

class SetupActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SetupScreenContent()
        }
    }
}

@Composable
private fun SetupScreenContent() {
    val configuration = LocalConfiguration.current
    val isWideScreen = configuration.screenWidthDp >= 600
    val activity = LocalContext.current as? ComponentActivity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "OpenKFZ Einrichtung",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(Modifier.height(24.dp))

        Column(
            modifier = Modifier.widthIn(max = if (isWideScreen) 560.dp else Double.MAX_VALUE.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    getSharedPreferences("openkfz", MODE_PRIVATE)
                        .edit()
                        .putString("role", "MASTER")
                        .apply()

                    activity?.let { currentActivity ->
                        currentActivity.startActivity(Intent(currentActivity, MasterActivity::class.java))
                    }
                }
            ) {
                Text("🖥 Dieses Gerät ist MASTER")
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    getSharedPreferences("openkfz", MODE_PRIVATE)
                        .edit()
                        .putString("role", "CLIENT")
                        .apply()

                    activity?.let { currentActivity ->
                        currentActivity.startActivity(Intent(currentActivity, ClientActivity::class.java))
                    }
                }
            ) {
                Text("🚗 Dieses Gerät ist CLIENT")
            }
        }
    }
}
