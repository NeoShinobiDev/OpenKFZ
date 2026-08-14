package com.openkfz.client

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.content.Intent
import android.view.Gravity
import android.widget.*

import com.openkfz.app.qr.ClientQrData
import com.openkfz.app.qr.QrGenerator
import com.openkfz.ui.CameraActivity

class ClientActivity : Activity(){

override fun onCreate(savedInstanceState: Bundle?) {

super.onCreate(savedInstanceState)

val density = resources.displayMetrics.density
fun dp(value: Int) = (value * density).toInt()

val root = LinearLayout(this)

root.orientation = LinearLayout.VERTICAL
root.gravity = Gravity.CENTER
root.setPadding(dp(32), dp(32), dp(32), dp(32))
root.setBackgroundColor(Color.parseColor("#F7F9FC"))

// Text sizes mirror Material3's default type scale (titleLarge/bodyLarge/labelLarge),
// since this legacy View screen has no MaterialTheme.typography to draw on.
val title = TextView(this)
title.text = "Client Einrichtung"
title.textSize = 22f
title.setTextColor(Color.parseColor("#0D47A1"))
title.setTypeface(null, Typeface.BOLD)
title.gravity = Gravity.CENTER

val qrLabel = TextView(this)
qrLabel.text = "Diesen QR-Code am Master scannen lassen:"
qrLabel.textSize = 16f
qrLabel.gravity = Gravity.CENTER
qrLabel.setTextColor(Color.parseColor("#455A64"))
qrLabel.setPadding(0, dp(24), 0, dp(16))

val qrImage = ImageView(this)
val qrData = ClientQrData.create(this)
qrImage.setImageBitmap(QrGenerator.create(qrData, size = 500))
qrImage.layoutParams = LinearLayout.LayoutParams(dp(220), dp(220))

val start = Button(this)
start.text = "Kamera starten"
start.textSize = 14f
start.setTextColor(Color.WHITE)
start.setPadding(dp(24), dp(16), dp(24), dp(16))

val buttonBackground = GradientDrawable()
buttonBackground.setColor(Color.parseColor("#2196F3"))
buttonBackground.cornerRadius = dp(24).toFloat()
start.background = buttonBackground

val startParams = LinearLayout.LayoutParams(
    LinearLayout.LayoutParams.WRAP_CONTENT,
    LinearLayout.LayoutParams.WRAP_CONTENT
)
startParams.topMargin = dp(32)
start.layoutParams = startParams

start.setOnClickListener {

startActivity(Intent(this, CameraActivity::class.java))

}

root.addView(title)
root.addView(qrLabel)
root.addView(qrImage)
root.addView(start)

setContentView(root)

}

}
