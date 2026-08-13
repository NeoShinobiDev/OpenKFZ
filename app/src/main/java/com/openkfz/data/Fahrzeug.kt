package com.openkfz.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fahrzeuge")
data class Fahrzeug(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val halter: String,
    val kennzeichen: String,
    val fin: String,
    val hersteller: String,
    val modell: String,
    val baujahr: Int,
    val dokumentpfad: String? = null,
    val erstellungsdatum: Long = System.currentTimeMillis()
)
