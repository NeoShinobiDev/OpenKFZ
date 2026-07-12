package com.openkfz.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.openkfz.app.ui.MasterActivity
import com.openkfz.app.ui.start.StartScreen
import com.openkfz.client.ClientActivity


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        setContent {


            StartScreen(

                onMaster = {

                    startActivity(
                        Intent(
                            this,
                            MasterActivity::class.java
                        )
                    )

                    finish()

                },


                onClient = {

                    startActivity(
                        Intent(
                            this,
                            ClientActivity::class.java
                        )
                    )

                    finish()

                }

            )


        }

    }

}
