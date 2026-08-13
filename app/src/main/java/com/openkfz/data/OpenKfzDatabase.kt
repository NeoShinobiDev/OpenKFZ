package com.openkfz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Fahrzeug::class], version = 1, exportSchema = false)
abstract class OpenKfzDatabase : RoomDatabase() {

    abstract fun fahrzeugDao(): FahrzeugDao

    companion object {
        @Volatile
        private var INSTANCE: OpenKfzDatabase? = null

        fun getInstance(context: Context): OpenKfzDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    OpenKfzDatabase::class.java,
                    "openkfz.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
