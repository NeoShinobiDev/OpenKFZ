package com.openkfz.app.files

import android.content.Context
import java.io.File

fun documentsDir(context: Context): File {

    val dir = File(context.getExternalFilesDir(null), "documents")

    if (!dir.exists()) {
        dir.mkdirs()
    }

    return dir

}
