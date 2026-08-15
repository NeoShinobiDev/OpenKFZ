package com.openkfz.app.ui.files

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.openkfz.app.files.documentsDir
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val IMAGE_EXTENSIONS = setOf("jpg", "jpeg", "png")

private fun formatSize(bytes: Long): String {

    if (bytes < 1024) return "$bytes B"

    val kb = bytes / 1024.0

    if (kb < 1024) return "%.1f KB".format(kb)

    return "%.1f MB".format(kb / 1024.0)

}

@Composable
fun FileManagerScreen(){

    val context = LocalContext.current

    var files by remember {
        mutableStateOf(documentsDir(context).listFiles()?.sortedByDescending { it.lastModified() } ?: emptyList())
    }

    var previewFile by remember { mutableStateOf<File?>(null) }

    fun refresh() {
        files = documentsDir(context).listFiles()?.sortedByDescending { it.lastModified() } ?: emptyList()
    }

    Column {

        Text(
            text = "Dateien",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = documentsDir(context).absolutePath,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {

                val dateFormat = SimpleDateFormat("yyyy-MM-dd_HHmmss", Locale.GERMANY)
                val file = File(documentsDir(context), "test_${dateFormat.format(Date())}.txt")
                file.writeText("Testdatei, erstellt am ${Date()}")

                refresh()

            }
        ) {
            Text("Testdatei erstellen")
        }

        Spacer(Modifier.height(16.dp))

        if (files.isEmpty()) {

            Text("Noch keine Dateien vorhanden.")

        } else {

            Text(
                text = "${files.size} Datei(en)",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(8.dp))

            LazyColumn {

                items(files) { file ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { previewFile = file }
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {

                            Text(text = file.name, style = MaterialTheme.typography.bodyLarge)

                            Text(
                                text = formatSize(file.length()),
                                style = MaterialTheme.typography.bodySmall
                            )

                        }

                        IconButton(
                            onClick = {
                                file.delete()
                                refresh()
                            }
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Löschen")
                        }

                    }

                    HorizontalDivider()

                }

            }

        }

    }

    previewFile?.let { file ->

        AlertDialog(
            onDismissRequest = { previewFile = null },
            title = { Text(file.name) },
            text = {

                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                    if (file.extension.lowercase() in IMAGE_EXTENSIONS) {

                        val bitmap = remember(file) {
                            BitmapFactory.decodeFile(file.absolutePath)
                        }

                        if (bitmap != null) {

                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = file.name,
                                modifier = Modifier.fillMaxWidth()
                            )

                        } else {

                            Text("Bild konnte nicht geladen werden.")

                        }

                    } else {

                        Text(
                            text = try {
                                file.readText()
                            } catch (e: Exception) {
                                "Keine Textvorschau verfügbar."
                            }
                        )

                    }

                }

            },
            confirmButton = {
                TextButton(onClick = { previewFile = null }) {
                    Text("Schließen")
                }
            }
        )

    }

}
