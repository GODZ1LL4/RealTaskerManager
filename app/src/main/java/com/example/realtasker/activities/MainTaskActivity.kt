package com.example.realtasker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realtasker.R
import com.example.realtasker.TareaViewModel
import com.example.realtasker.adapter.TareasAdapter
import com.example.realtasker.databinding.ActivityMainTaskBinding

class MainTaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainTaskBinding
    lateinit var viewModel: TareaViewModel
    lateinit var navController: NavController
    lateinit var menu: Menu

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_usuario, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.mostrar_perfil -> {
                infoUsuario()
                true
            }
            R.id.modificar_perfil -> {

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun infoUsuario() {
        val intent = Intent(this, InfoUsuarioActivity::class.java)
        startActivity(intent)
    }
}