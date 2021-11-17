package com.example.realtasker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realtasker.TareaViewModel
import com.example.realtasker.adapter.TareasAdapter
import com.example.realtasker.databinding.ActivityMainTaskBinding

class MainTaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainTaskBinding
    lateinit var viewModel: TareaViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainTaskBinding.inflate(layoutInflater)


        val recyclerView = binding.recyclerviewTareas
        val adapter = TareasAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(TareaViewModel::class.java)

        viewModel.allTareas.observe(this, Observer { tareas ->
            tareas?.let { adapter.setTareas(it) }
        })

        binding.fab.setOnClickListener {
            val intent = Intent(this, AgregarTareaActivity::class.java)
            startActivity(intent)
        }



        setContentView(binding.root)
    }
}