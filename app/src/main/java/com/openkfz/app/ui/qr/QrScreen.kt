package com.openkfz.app.ui.qr

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.openkfz.app.qr.QrGenerator
import com.openkfz.app.qr.MasterQrData

@Composable
fun QrScreen(){

    val context = LocalContext.current

    val data = remember {
       MasterQrData.create(context)
    }

    val qr = remember {
       QrGenerator.create(data)
    }

    Column(
       modifier = Modifier.padding(16.dp),
       horizontalAlignment = Alignment.CenterHorizontally
    ) {

       Text(
           "Master-QR-Code",
           style = MaterialTheme.typography.titleLarge
       )

       Spacer(Modifier.height(8.dp))

       Text(
           "Von einem Client scannen, um dieses Gerät zu verbinden.",
           style = MaterialTheme.typography.bodyMedium
       )

       Spacer(Modifier.height(16.dp))

       Image(
           bitmap = qr.asImageBitmap(),
           contentDescription = "Master QR",
           modifier = Modifier
               .width(240.dp)
               .height(240.dp)
       )

       Spacer(Modifier.height(16.dp))

       Text(
           text = data,
           style = MaterialTheme.typography.bodySmall
       )

    }

}
