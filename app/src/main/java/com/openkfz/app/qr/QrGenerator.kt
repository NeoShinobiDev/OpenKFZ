package com.openkfz.app.qr

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix


object QrGenerator {


    fun create(
        text:String,
        size:Int = 800
    ): Bitmap {


        val matrix: BitMatrix =
            MultiFormatWriter()
                .encode(
                    text,
                    BarcodeFormat.QR_CODE,
                    size,
                    size
                )


        val bitmap =
            Bitmap.createBitmap(
                size,
                size,
                Bitmap.Config.RGB_565
            )


        for(x in 0 until size){

            for(y in 0 until size){

                bitmap.setPixel(
                    x,
                    y,
                    if(matrix[x,y])
                        android.graphics.Color.BLACK
                    else
                        android.graphics.Color.WHITE
                )

            }

        }


        return bitmap

    }

}
