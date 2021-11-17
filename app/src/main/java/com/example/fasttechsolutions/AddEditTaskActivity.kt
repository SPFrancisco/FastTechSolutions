package com.example.fasttechsolutions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fasttechsolutions.database.Task
import com.example.fasttechsolutions.databinding.ActivityAddEditTaskBinding
import com.example.fasttechsolutions.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddEditTaskActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddEditTaskBinding
    lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_edit_task)
        binding = ActivityAddEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TaskViewModel::class.java)

        val taskType = intent.getStringExtra("taskType")
        var id: Int = 0
        if(taskType.equals("Edit")) {
            binding.btnAddUpdate.text = "Update Task"
            binding.taskEdit.setText(intent.getStringExtra("taskTitle"))
            binding.taskDescriptionEdit.setText(intent.getStringExtra("taskDescription"))
            id = intent.getIntExtra("taskID", -1)
        }else {
            binding.btnAddUpdate.text = "Save Task"
        }

        binding.btnAddUpdate.setOnClickListener {
            if(taskType.equals("Edit")) {
                if(!TextUtils.isEmpty(binding.taskEdit.text) && !TextUtils.isEmpty(binding.taskDescriptionEdit.text)) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    val updateTask = Task(id,binding.taskEdit.text.toString(),binding.taskEdit.text.toString(),currentDate)
                    viewModel.updateTask(updateTask)
                    Toast.makeText(this, "Task Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if(!TextUtils.isEmpty(binding.taskEdit.text) && !TextUtils.isEmpty(binding.taskDescriptionEdit.text)) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    viewModel.addTask(Task(id,binding.taskEdit.text.toString(),binding.taskEdit.text.toString(),currentDate))
                    Toast.makeText(this, "Task Added..", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, TaskListActivity::class.java))
            this.finish()
        }
    }
}