package com.openkfz.client

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.content.Intent
import android.view.Gravity
import android.widget.*

import com.openkfz.ui.CameraActivity

class ClientActivity : Activity(){

override fun onCreate(savedInstanceState: Bundle?) {

super.onCreate(savedInstanceState)

val density = resources.displayMetrics.density
fun dp(value: Int) = (value * density).toInt()

val root = LinearLayout(this)

root.orientation = LinearLayout.VERTICAL
root.gravity = Gravity.CENTER
root.setPadding(dp(30), dp(30), dp(30), dp(30))
root.setBackgroundColor(Color.parseColor("#F7F9FC"))

val title = TextView(this)
title.text = "Client Einrichtung"
title.textSize = 26f
title.setTextColor(Color.parseColor("#0D47A1"))
title.setTypeface(null, Typeface.BOLD)
title.gravity = Gravity.CENTER

val qr = TextView(this)
qr.text = """
QR Code Verbindung

(später)

Master suchen
""".trimIndent()

qr.textSize = 18f
qr.gravity = Gravity.CENTER
qr.setTextColor(Color.parseColor("#455A64"))
qr.setPadding(0, dp(24), 0, dp(24))

val start = Button(this)
start.text = "Kamera starten"
start.setTextColor(Color.WHITE)
start.setPadding(dp(24), dp(12), dp(24), dp(12))

val buttonBackground = GradientDrawable()
buttonBackground.setColor(Color.parseColor("#2196F3"))
buttonBackground.cornerRadius = dp(24).toFloat()
start.background = buttonBackground

start.setOnClickListener {

startActivity(Intent(this, CameraActivity::class.java))

}

root.addView(title)
root.addView(qr)
root.addView(start)

setContentView(root)

}

}
