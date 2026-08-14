package com.openkfz.app.ui.settings

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.openkfz.app.ui.theme.AppSettings

@Composable
fun SettingsScreen(){

    val context = LocalContext.current

    Column {

        Text(
            text = "Einstellungen",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Darstellung",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(8.dp))

        DarkModeOption("System", AppSettings.MODE_SYSTEM, context)
        DarkModeOption("Hell", AppSettings.MODE_LIGHT, context)
        DarkModeOption("Dunkel", AppSettings.MODE_DARK, context)

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Über",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(8.dp))

        val versionName = try {
            context.packageManager.getPackageInfo(context.packageName, 0).versionName
        } catch (e: Exception) {
            "-"
        }

        Text(
            text = "OpenKFZ – Version $versionName",
            style = MaterialTheme.typography.bodyMedium
        )

    }

}

@Composable
private fun DarkModeOption(label: String, mode: String, context: Context) {

    val selected = AppSettings.darkModePreference.value == mode

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = { AppSettings.setDarkModePreference(context, mode) }
            )
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(
            selected = selected,
            onClick = { AppSettings.setDarkModePreference(context, mode) }
        )

        Spacer(Modifier.width(8.dp))

        Text(text = label, style = MaterialTheme.typography.bodyLarge)

    }

}
