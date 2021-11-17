package com.example.realtasker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.realtasker.TareaViewModel
import com.example.realtasker.databinding.ActivityActualizarTareaBinding
import com.example.realtasker.model.Tarea

class ActualizarTareaActivity : AppCompatActivity() {

    lateinit var binding: ActivityActualizarTareaBinding
    lateinit var viewModel: TareaViewModel
    lateinit var tarea: Tarea

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding   = ActivityActualizarTareaBinding.inflate(layoutInflater)

        var intent = intent

        tarea = intent.getParcelableExtra<Tarea>("tareaObject") as Tarea
        binding.actualizartareaTxt.setText(tarea.Descripcion)

        if(tarea.Completada){
            binding.btnCompletar.setText("Reestablecer")
        }
        else{
            binding.btnCompletar.setText("Completada")
        }

        viewModel = ViewModelProvider(this).get(TareaViewModel::class.java)

        binding.btnActualizar.setOnClickListener {
            val descripcionNueva = binding.actualizartareaTxt.text.toString()
            val actualizarTarea = Tarea(tarea.Id, descripcionNueva, tarea.Completada)
            actualizarTarea(actualizarTarea)
        }

        binding.btnEliminar.setOnClickListener {
            val _dialog = AlertDialog.Builder(this)
            _dialog.setPositiveButton("Si"){_ , _ ->
                viewModel.delete(tarea)
                Toast.makeText(this, "Tarea eliminada", Toast.LENGTH_SHORT).show()
                this.finish()
            }
            _dialog.setNegativeButton("No"){_ , _ ->

            }

            _dialog.setTitle("Eliminando tarea")
            _dialog.setMessage("Seguro que quieres eliminar la tarea?")
            _dialog.create().show()
        }

        binding.btnCompletar.setOnClickListener {
            var actualizarTarea = Tarea(tarea.Id,tarea.Descripcion,tarea.Completada)

            if(tarea.Completada){
                actualizarTarea.Completada = false
                actualizarTarea(actualizarTarea)
            }
            else{
                actualizarTarea.Completada = true
                actualizarTarea(actualizarTarea)
            }
        }

        setContentView(binding.root)
    }




    private fun actualizarTarea(tareaActualizada: Tarea){
        if (binding.actualizartareaTxt.text.isEmpty() || binding.actualizartareaTxt.text.isBlank()){
            Toast.makeText(this, "Debe llenar el campo", Toast.LENGTH_LONG).show()
        }
        else {
            viewModel.update(tareaActualizada)
            Toast.makeText(this, "Tarea actualizada con exito", Toast.LENGTH_SHORT).show()
            this.finish()
        }
    }
}