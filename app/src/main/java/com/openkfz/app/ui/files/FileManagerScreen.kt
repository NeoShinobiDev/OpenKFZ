package com.openkfz.app.ui.files

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private fun documentsDir(context: Context): File {

    val dir = File(context.getExternalFilesDir(null), "documents")

    if (!dir.exists()) {
        dir.mkdirs()
    }

    return dir

}

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

}
