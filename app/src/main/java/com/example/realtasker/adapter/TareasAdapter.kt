package com.example.realtasker.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.realtasker.activities.ActualizarTareaActivity
import com.example.realtasker.R
import com.example.realtasker.model.Tarea





class TareasAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<TareasAdapter.TaskViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var tareas = emptyList<Tarea>()

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tareaItemView: TextView = itemView.findViewById(R.id.textview_tarea)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = inflater.inflate(R.layout.tarea_list_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = tareas[position]
        holder.tareaItemView.text = current.Descripcion

        holder.tareaItemView.setOnClickListener {
            val context = holder.tareaItemView.context
            val intent = Intent(context, ActualizarTareaActivity::class.java)

            intent.putExtra("tareaObject", current)
            ContextCompat.startActivity(context, intent, Bundle.EMPTY)
        }

        if (current.Completada) {
            holder.tareaItemView.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
        } else {
            holder.tareaItemView.apply {
                paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }

    internal fun setTareas(tareas: List<Tarea>) {
        this.tareas = tareas
        notifyDataSetChanged()
    }

    override fun getItemCount() = tareas.size
}