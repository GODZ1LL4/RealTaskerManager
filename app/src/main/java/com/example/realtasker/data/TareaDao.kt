package com.example.realtasker.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.realtasker.model.Tarea


@Dao
interface TareaDao {
    @Query("SELECT * from tarea_table")
    fun getTareas(): LiveData<List<Tarea>>

    @Insert
    suspend fun insert(tarea: Tarea)

    @Update
    suspend fun update(tarea: Tarea)

    @Delete
    suspend fun delete(tarea: Tarea)
}