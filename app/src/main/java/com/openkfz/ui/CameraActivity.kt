package com.openkfz.ui

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.*

class CameraActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val frame = FrameLayout(this)

        val text = TextView(this)
        text.text = """
OpenKfz Camera

Kamera Berechtigung benötigt

AUTO CAMERA SCREEN
"""
        text.textSize = 20f
        text.gravity = Gravity.CENTER

        frame.addView(text)

        val settings = Button(this)
        settings.text = "⚙"

        val params = FrameLayout.LayoutParams(
            120,
            120
        )

        params.gravity = Gravity.BOTTOM or Gravity.END

        frame.addView(settings, params)

        setContentView(frame)
    }
}
