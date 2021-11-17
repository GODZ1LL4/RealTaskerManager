package com.example.realtasker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.realtasker.model.Tarea
import com.example.realtasker.data.TareasDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TareaViewModel (application: Application) : AndroidViewModel(application) {

    private val repository : TareaRepository

    val allTareas : LiveData<List<Tarea>>

    init {
        val tareaDao = TareasDatabase.getInstance(application).tareaDao()
        repository = TareaRepository(tareaDao)
        allTareas = repository.allTareas
    }

    fun insert(tarea :Tarea) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(tarea)
    }

    fun update(tarea :Tarea) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(tarea)
    }

    fun delete(tarea :Tarea) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(tarea)
    }
}