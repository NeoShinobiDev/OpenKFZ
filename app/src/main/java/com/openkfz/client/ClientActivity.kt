package com.openkfz.client

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.openkfz.app.ui.client.ClientQrScreen
import com.openkfz.ui.CameraActivity


class ClientActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ClientQrScreen()
        }
    }
}

setContentView(root)

}

}
