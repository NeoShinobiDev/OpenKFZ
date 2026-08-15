package com.openkfz.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.core.ImageCapture.OutputFileOptions
import androidx.camera.core.ImageCapture.OutputFileResults
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.openkfz.app.files.documentsDir
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CameraActivity : ComponentActivity() {

    private lateinit var previewView: PreviewView
    private var imageCapture: ImageCapture? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val density = resources.displayMetrics.density
        fun dp(value: Int) = (value * density).toInt()

        val frame = FrameLayout(this)

        previewView = PreviewView(this)
        frame.addView(previewView)

        val settings = Button(this)
        settings.text = "⚙"

        val settingsParams = FrameLayout.LayoutParams(dp(56), dp(56))
        settingsParams.gravity = Gravity.BOTTOM or Gravity.END
        settingsParams.setMargins(0, 0, dp(16), dp(16))

        frame.addView(settings, settingsParams)

        val capture = Button(this)
        capture.text = "●"
        capture.textSize = 28f
        capture.setTextColor(Color.parseColor("#2196F3"))

        val captureParams = FrameLayout.LayoutParams(dp(72), dp(72))
        captureParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        captureParams.setMargins(0, 0, 0, dp(24))

        capture.setOnClickListener { takePhoto() }

        frame.addView(capture, captureParams)

        setContentView(frame)

        if (hasCameraPermission()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        }

    }

    private fun hasCameraPermission(): Boolean {

        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                Toast.makeText(this, "Kamera-Berechtigung benötigt", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun startCamera() {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({

            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            val capture = ImageCapture.Builder().build()
            imageCapture = capture

            try {

                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    this,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    preview,
                    capture
                )

            } catch (e: Exception) {

                Toast.makeText(this, "Kamera konnte nicht gestartet werden", Toast.LENGTH_LONG).show()

            }

        }, ContextCompat.getMainExecutor(this))

    }

    private fun takePhoto() {

        val capture = imageCapture ?: return

        val dateFormat = SimpleDateFormat("yyyy-MM-dd_HHmmss", Locale.GERMANY)
        val file = File(documentsDir(this), "scan_${dateFormat.format(Date())}.jpg")

        val outputOptions = OutputFileOptions.Builder(file).build()

        capture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {

                override fun onImageSaved(output: OutputFileResults) {
                    Toast.makeText(this@CameraActivity, "Gespeichert: ${file.name}", Toast.LENGTH_LONG).show()
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(this@CameraActivity, "Aufnahme fehlgeschlagen", Toast.LENGTH_LONG).show()
                }

            }
        )

    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 100
    }

}
