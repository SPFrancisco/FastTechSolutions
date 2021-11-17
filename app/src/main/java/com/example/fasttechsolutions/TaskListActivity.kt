package com.example.fasttechsolutions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fasttechsolutions.adapters.TaskAdapter
import com.example.fasttechsolutions.adapters.TaskClickDeteleInterface
import com.example.fasttechsolutions.adapters.TaskClickInterface
import com.example.fasttechsolutions.database.Task
import com.example.fasttechsolutions.databinding.ActivityTaskListBinding
import com.example.fasttechsolutions.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskListActivity : AppCompatActivity(), TaskClickInterface, TaskClickDeteleInterface {

    lateinit var binding: ActivityTaskListBinding
    lateinit var taskRV: RecyclerView
    //lateinit var addFAB: FloatingActionButton
    lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_task_list)
        binding = ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskRV = findViewById(R.id.recycle_view)
        taskRV.layoutManager = LinearLayoutManager(this)

        val taskAdapter = TaskAdapter(this, this, this)
        taskRV.adapter = taskAdapter
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TaskViewModel::class.java)
        viewModel.allTask.observe(this, { list ->
            list?.let {
                taskAdapter.updateList(it)
            }
        })
        binding.btnAddTask.setOnClickListener {
            val intent = Intent(this@TaskListActivity, AddEditTaskActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onTaskClick(task: Task) {
        val intent = Intent(this@TaskListActivity, AddEditTaskActivity::class.java)
        intent.putExtra("taskType", "Edit")
        intent.putExtra("taskTitle", task.title)
        intent.putExtra("taskDescription", task.description)
        intent.putExtra("taskID", task.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(task: Task) {
        viewModel.deteleTask(task)
        Toast.makeText(this, "${task.title} Deleted", Toast.LENGTH_LONG).show()
    }
}