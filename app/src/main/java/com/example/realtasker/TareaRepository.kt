package com.example.realtasker

import androidx.lifecycle.LiveData
import com.example.realtasker.model.Tarea
import com.example.realtasker.data.TareaDao

class TareaRepository(private val tareaDao : TareaDao) {

    val allTareas : LiveData<List<Tarea>> = tareaDao.getTareas()

    suspend fun insert(tarea :Tarea) {
        tareaDao.insert(tarea)
    }

    suspend fun update(tarea :Tarea) {
        tareaDao.update(tarea);
    }

    suspend fun delete(tarea :Tarea) {
        tareaDao.delete(tarea)
    }
}