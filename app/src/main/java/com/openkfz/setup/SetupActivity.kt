package com.openkfz.setup

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.openkfz.app.ui.MasterActivity
import com.openkfz.client.ClientActivity

class SetupActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            ){

                Text(
                    "OpenKFZ Einrichtung",
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(
                    Modifier.height(40.dp)
                )


                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                        getSharedPreferences(
                            "openkfz",
                            MODE_PRIVATE
                        )
                        .edit()
                        .putString("role","MASTER")
                        .apply()


                        startActivity(
                            Intent(
                                this@SetupActivity,
                                MasterActivity::class.java
                            )
                        )

                        finish()

                    }
                ){
                    Text("🖥 Dieses Gerät ist MASTER")
                }


                Spacer(
                    Modifier.height(20.dp)
                )


                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                        getSharedPreferences(
                            "openkfz",
                            MODE_PRIVATE
                        )
                        .edit()
                        .putString("role","CLIENT")
                        .apply()


                        startActivity(
                            Intent(
                                this@SetupActivity,
                                ClientActivity::class.java
                            )
                        )

                        finish()

                    }
                ){
                    Text("🚗 Dieses Gerät ist CLIENT")
                }

            }

        }
    }
}
