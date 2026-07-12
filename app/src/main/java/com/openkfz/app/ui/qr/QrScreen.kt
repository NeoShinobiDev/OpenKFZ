package com.openkfz.app.ui.qr


import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.asImageBitmap

import com.openkfz.app.qr.QrGenerator
import com.openkfz.app.qr.MasterQrData


@Composable
fun QrScreen(){


    val context =
        LocalContext.current


    val data =
        remember {

            MasterQrData.create(context)

        }



    val qr =
        remember {

            QrGenerator.create(
                data
            )

        }



    Column(

        modifier =
        Modifier.padding(20.dp)

    ){


        Text(
            "📷 OpenKFZ Master QR"
        )


        Text(
            data
        )


        Image(

            bitmap =
            qr.asImageBitmap(),

            contentDescription =
            "Master QR"

        )


    }


}
