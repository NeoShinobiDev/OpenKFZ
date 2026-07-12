package com.openkfz.setup

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.*

import com.openkfz.client.ClientActivity

class SetupActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)

        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER


        val title = TextView(this)

        title.text = """
OpenKfz

Wähle Modus
"""
        title.textSize = 28f
        title.gravity = Gravity.CENTER

        layout.addView(title)


        val client = Button(this)

        client.text = "CLIENT"

        client.setOnClickListener {
            startActivity(
                android.content.Intent(
                    this,
                    ClientActivity::class.java
                )
            )
        }

        layout.addView(client)


        val master = Button(this)

        master.text = "MASTER"

        layout.addView(master)


        setContentView(layout)
    }
}
