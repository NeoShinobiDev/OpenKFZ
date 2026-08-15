package com.openkfz.data

import org.json.JSONArray
import org.json.JSONObject

fun List<Fahrzeug>.toJson(): String {

    val array = JSONArray()

    forEach { fahrzeug ->

        val obj = JSONObject()
        obj.put("halter", fahrzeug.halter)
        obj.put("kennzeichen", fahrzeug.kennzeichen)
        obj.put("fin", fahrzeug.fin)
        obj.put("hersteller", fahrzeug.hersteller)
        obj.put("modell", fahrzeug.modell)
        obj.put("baujahr", fahrzeug.baujahr)
        obj.put("dokumentpfad", fahrzeug.dokumentpfad)
        obj.put("erstellungsdatum", fahrzeug.erstellungsdatum)

        array.put(obj)

    }

    return array.toString(2)

}

fun parseFahrzeugeJson(json: String): List<Fahrzeug> {

    val array = JSONArray(json)

    return (0 until array.length()).map { index ->

        val obj = array.getJSONObject(index)

        Fahrzeug(
            halter = obj.getString("halter"),
            kennzeichen = obj.getString("kennzeichen"),
            fin = obj.getString("fin"),
            hersteller = obj.getString("hersteller"),
            modell = obj.getString("modell"),
            baujahr = obj.getInt("baujahr"),
            dokumentpfad = obj.optString("dokumentpfad").ifEmpty { null },
            erstellungsdatum = obj.optLong("erstellungsdatum", System.currentTimeMillis())
        )

    }

}
