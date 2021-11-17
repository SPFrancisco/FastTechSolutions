package com.example.fasttechsolutions.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fasttechsolutions.database.Task
import com.example.fasttechsolutions.database.TaskDatabase
import com.example.fasttechsolutions.database.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class TaskViewModel(application: Application) : AndroidViewModel(application) {

    val allTask: LiveData<List<Task>>
    val repository: TaskRepository

    init {
        val dao = TaskDatabase.getInstance(application).getTaskDao()
        repository = TaskRepository(dao)
        allTask = repository.allTask
    }

    fun addTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(task)
    }

    fun deteleTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(task)
    }
}