package com.openkfz.modules

import android.content.Context
import android.view.View

interface KfzModule {
    fun getName(): String
    fun createView(context: Context): View
}
