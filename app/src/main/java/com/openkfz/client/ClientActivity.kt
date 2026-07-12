package com.openkfz.client

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.view.Gravity
import android.widget.*

import com.openkfz.ui.CameraActivity

class ClientActivity : Activity(){

override fun onCreate(savedInstanceState: Bundle?) {

super.onCreate(savedInstanceState)

val root = LinearLayout(this)

root.orientation = LinearLayout.VERTICAL
root.gravity = Gravity.CENTER
root.setPadding(30,30,30,30)

val title = TextView(this)
title.text = "Client Einrichtung"
title.textSize = 26f

val qr = TextView(this)
qr.text = """
QR Code Verbindung

(später)

Master suchen
""".trimIndent()

qr.textSize = 18f
qr.gravity = Gravity.CENTER

val start = Button(this)
start.text = "Kamera starten"

start.setOnClickListener {

startActivity(Intent(this, CameraActivity::class.java))

}

root.addView(title)
root.addView(qr)
root.addView(start)

setContentView(root)

}

}
