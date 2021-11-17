package com.example.realtasker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.realtasker.TareaViewModel
import com.example.realtasker.databinding.ActivityAgregarTareaBinding
import com.example.realtasker.model.Tarea

class AgregarTareaActivity : AppCompatActivity() {

    lateinit var binding: ActivityAgregarTareaBinding
    lateinit var viewModel: TareaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarTareaBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(TareaViewModel::class.java)

        binding.btnGuardar.setOnClickListener {
            val tarea = Tarea(0,binding.edittxtTarea.text.toString(),false)
            viewModel.insert(tarea)
            BackMain()
        }

        setContentView(binding.root)
    }

    fun BackMain(){
        val intent = Intent(this, MainTaskActivity::class.java)
        startActivity(intent)
    }
}

