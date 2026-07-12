package com.openkfz.app

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.content.SharedPreferences
import com.openkfz.setup.SetupActivity
import com.openkfz.client.ClientActivity


class MainActivity : Activity(){

override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)


val prefs: SharedPreferences =
getSharedPreferences("openkfz", MODE_PRIVATE)


val setupDone =
prefs.getBoolean("setup_done", false)


if(!setupDone){

startActivity(
Intent(this, SetupActivity::class.java)
)

}else{

startActivity(
Intent(this, ClientActivity::class.java)
)

}


finish()

}

}
