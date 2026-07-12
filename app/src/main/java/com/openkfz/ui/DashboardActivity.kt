package com.openkfz.ui

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class DashboardActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)

        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(32,32,32,32)


        val title = TextView(this)

        title.text = "🚗 OpenKfz Dashboard"
        title.textSize = 32f

        layout.addView(title)


        val modules = arrayOf(
            "🚗 Fahrzeug",
            "📷 Kamera Client",
            "🔧 Diagnose",
            "📡 Netzwerk",
            "⚙ Einstellungen"
        )


        for(module in modules){

            val button = Button(this)

            button.text = module
            button.textSize = 18f

            layout.addView(button)
        }


        setContentView(layout)
    }
}
