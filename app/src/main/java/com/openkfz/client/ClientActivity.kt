package com.openkfz.client
import androidx.compose.ui.unit.dp
import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import com.openkfz.app.R


class ClientActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            100
        )

        setContent {
            ClientScreen()
        }
    }
}


@Composable
fun ClientScreen() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        CameraPreview()


        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            containerColor = androidx.compose.ui.graphics.Color.White
        ) {}


        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {

            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings"
            )

        }
    }
}


@Composable
fun CameraPreview() {

    AndroidView(
        factory = { context ->

            PreviewView(context)

        },
        modifier = Modifier.fillMaxSize()
    )
}
