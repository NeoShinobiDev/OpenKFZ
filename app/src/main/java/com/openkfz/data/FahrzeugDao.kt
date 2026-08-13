package com.openkfz.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FahrzeugDao {

    @Insert
    suspend fun insert(fahrzeug: Fahrzeug): Long

    @Update
    suspend fun update(fahrzeug: Fahrzeug)

    @Delete
    suspend fun delete(fahrzeug: Fahrzeug)

    @Query("SELECT * FROM fahrzeuge ORDER BY erstellungsdatum DESC")
    fun getAll(): Flow<List<Fahrzeug>>

    @Query("SELECT * FROM fahrzeuge WHERE id = :id")
    suspend fun getById(id: Long): Fahrzeug?
}
