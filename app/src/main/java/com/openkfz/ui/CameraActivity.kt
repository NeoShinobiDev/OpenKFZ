package com.openkfz.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
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
import androidx.core.content.ContextCompat
import androidx.activity.result.contract.ActivityResultContracts
import com.openkfz.app.files.documentsDir
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CameraActivity : ComponentActivity() {

    private lateinit var previewView: PreviewView
    private lateinit var reviewOverlay: FrameLayout
    private lateinit var reviewImage: ImageView
    private lateinit var captureButton: Button
    private lateinit var settingsButton: Button
    private var imageCapture: ImageCapture? = null
    private var pendingCaptureFile: File? = null
    private var flashMode = ImageCapture.FLASH_MODE_OFF

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            startCamera()
        } else {
            Toast.makeText(this, "Kamera-Berechtigung benötigt", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val density = resources.displayMetrics.density
        val dp = { value: Int -> (value * density).toInt() }

        val frame = FrameLayout(this)

        previewView = PreviewView(this)
        frame.addView(previewView)

        settingsButton = Button(this)
        settingsButton.setOnClickListener { toggleFlash() }

        val settingsParams = FrameLayout.LayoutParams(dp(56), dp(56))
        settingsParams.gravity = Gravity.BOTTOM or Gravity.END
        settingsParams.setMargins(0, 0, dp(16), dp(16))

        frame.addView(settingsButton, settingsParams)

        updateFlashButtonAppearance()

        captureButton = Button(this)
        captureButton.text = "●"
        captureButton.textSize = 28f
        captureButton.setTextColor(Color.parseColor("#2196F3"))

        val captureParams = FrameLayout.LayoutParams(dp(72), dp(72))
        captureParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        captureParams.setMargins(0, 0, 0, dp(24))

        captureButton.setOnClickListener { takePhoto() }

        frame.addView(captureButton, captureParams)

        buildReviewOverlay(dp)
        frame.addView(reviewOverlay)

        setContentView(frame)

        if (hasCameraPermission()) {
            startCamera()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

    }

    private fun buildReviewOverlay(dp: (Int) -> Int) {

        reviewOverlay = FrameLayout(this)
        reviewOverlay.setBackgroundColor(Color.BLACK)
        reviewOverlay.visibility = android.view.View.GONE

        reviewImage = ImageView(this)
        reviewImage.scaleType = ImageView.ScaleType.FIT_CENTER
        reviewOverlay.addView(
            reviewImage,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )

        val buttonRow = LinearLayout(this)
        buttonRow.orientation = LinearLayout.HORIZONTAL
        buttonRow.gravity = Gravity.CENTER

        val discard = Button(this)
        discard.text = "Verwerfen"
        discard.setTextColor(Color.WHITE)
        discard.setPadding(dp(24), dp(16), dp(24), dp(16))
        val discardBg = GradientDrawable()
        discardBg.setColor(Color.parseColor("#616161"))
        discardBg.cornerRadius = dp(24).toFloat()
        discard.background = discardBg
        discard.setOnClickListener { discardCapture() }

        val discardParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        discardParams.setMargins(0, 0, dp(16), 0)

        val confirm = Button(this)
        confirm.text = "Übernehmen"
        confirm.setTextColor(Color.WHITE)
        confirm.setPadding(dp(24), dp(16), dp(24), dp(16))
        val confirmBg = GradientDrawable()
        confirmBg.setColor(Color.parseColor("#2196F3"))
        confirmBg.cornerRadius = dp(24).toFloat()
        confirm.background = confirmBg
        confirm.setOnClickListener { confirmCapture() }

        buttonRow.addView(discard, discardParams)
        buttonRow.addView(confirm)

        val rowParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        )
        rowParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        rowParams.setMargins(0, 0, 0, dp(32))

        reviewOverlay.addView(buttonRow, rowParams)

    }

    private fun toggleFlash() {

        flashMode = when (flashMode) {
            ImageCapture.FLASH_MODE_OFF -> ImageCapture.FLASH_MODE_AUTO
            ImageCapture.FLASH_MODE_AUTO -> ImageCapture.FLASH_MODE_ON
            else -> ImageCapture.FLASH_MODE_OFF
        }

        imageCapture?.flashMode = flashMode
        updateFlashButtonAppearance()

    }

    private fun updateFlashButtonAppearance() {

        val (label, color) = when (flashMode) {
            ImageCapture.FLASH_MODE_ON -> "⚡+" to Color.parseColor("#FFC107")
            ImageCapture.FLASH_MODE_AUTO -> "⚡A" to Color.parseColor("#2196F3")
            else -> "⚡-" to Color.parseColor("#9E9E9E")
        }

        settingsButton.text = label
        settingsButton.setTextColor(color)

    }

    private fun hasCameraPermission(): Boolean {

        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED

    }

    private fun startCamera() {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({

            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            val capture = ImageCapture.Builder().setFlashMode(flashMode).build()
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
        val tempFile = File(cacheDir, "pending_scan_${dateFormat.format(Date())}.jpg")

        val outputOptions = OutputFileOptions.Builder(tempFile).build()

        captureButton.isEnabled = false

        capture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {

                override fun onImageSaved(output: OutputFileResults) {
                    captureButton.isEnabled = true
                    showReview(tempFile)
                }

                override fun onError(exception: ImageCaptureException) {
                    captureButton.isEnabled = true
                    Toast.makeText(this@CameraActivity, "Aufnahme fehlgeschlagen", Toast.LENGTH_LONG).show()
                }

            }
        )

    }

    private fun showReview(file: File) {

        pendingCaptureFile = file

        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        reviewImage.setImageBitmap(bitmap)

        reviewOverlay.visibility = android.view.View.VISIBLE
        captureButton.visibility = android.view.View.GONE
        settingsButton.visibility = android.view.View.GONE

    }

    private fun hideReview() {

        reviewOverlay.visibility = android.view.View.GONE
        captureButton.visibility = android.view.View.VISIBLE
        settingsButton.visibility = android.view.View.VISIBLE

    }

    private fun discardCapture() {

        pendingCaptureFile?.delete()
        pendingCaptureFile = null

        hideReview()

    }

    private fun confirmCapture() {

        val temp = pendingCaptureFile ?: return

        val dateFormat = SimpleDateFormat("yyyy-MM-dd_HHmmss", Locale.GERMANY)
        val finalFile = File(documentsDir(this), "scan_${dateFormat.format(Date())}.jpg")

        val saved = try {
            temp.copyTo(finalFile, overwrite = true)
            temp.delete()
            true
        } catch (e: Exception) {
            false
        }

        if (saved) {
            Toast.makeText(this, "Gespeichert: ${finalFile.name}", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Speichern fehlgeschlagen", Toast.LENGTH_LONG).show()
        }

        pendingCaptureFile = null
        hideReview()

    }

}
