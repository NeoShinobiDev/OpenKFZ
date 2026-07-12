package com.openkfz.camera


import android.app.Activity
import android.os.Bundle
import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.widget.*
import android.view.Gravity


class CameraActivity:Activity(){


override fun onCreate(savedInstanceState:Bundle?){
super.onCreate(savedInstanceState)


if(checkSelfPermission(Manifest.permission.CAMERA)
!= PackageManager.PERMISSION_GRANTED){

requestPermissions(
arrayOf(Manifest.permission.CAMERA),
100
)

}



val root=FrameLayout(this)

root.setBackgroundColor(Color.BLACK)



val photo=Button(this)

photo.text="●"


val photoParams=
FrameLayout.LayoutParams(
180,
180
)

photoParams.gravity=Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL

photoParams.bottomMargin=40


root.addView(photo,photoParams)



val settings=Button(this)

settings.text="⚙"



val settingsParams=
FrameLayout.LayoutParams(
120,
120
)

settingsParams.gravity=
Gravity.BOTTOM or Gravity.RIGHT

settingsParams.rightMargin=30

settingsParams.bottomMargin=30


root.addView(settings,settingsParams)



setContentView(root)

}


}
