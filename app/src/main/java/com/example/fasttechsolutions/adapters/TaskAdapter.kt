package com.example.fasttechsolutions.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fasttechsolutions.R
import com.example.fasttechsolutions.database.Task

class TaskAdapter(
    val context: Context,
    val taskClickInterface: TaskClickInterface,
    val taskClickDeteleInterface: TaskClickDeteleInterface
                  ) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private val allTask = ArrayList<Task>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val taskTitle = itemView.findViewById<TextView>(R.id.txt_taskTitle)
        val timeStamp = itemView.findViewById<TextView>(R.id.txt_timeStamp)
        val deleteIcon = itemView.findViewById<ImageView>(R.id.iv_delete)
    }

    // Crear ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_list, parent, false)
        return ViewHolder(view)
    }

    // Enlazar los datos
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskTitle.setText(allTask.get(position).title)
        holder.timeStamp.setText("Last Updated: " + allTask.get(position).timeStamp)

        holder.itemView.setOnClickListener {
            taskClickInterface.onTaskClick(allTask.get(position))
        }

        holder.deleteIcon.setOnClickListener {
            taskClickDeteleInterface.onDeleteIconClick(allTask.get(position))
        }
    }

    // Retonar instacia de la coleccion
    override fun getItemCount(): Int {
        return allTask.size
    }

    fun updateList(newList : List<Task>) {
        allTask.clear()
        allTask.addAll(newList)
        notifyDataSetChanged()
    }
}

interface TaskClickInterface {
    fun onTaskClick(task: Task)
}

interface TaskClickDeteleInterface {
    fun onDeleteIconClick(task: Task)
}