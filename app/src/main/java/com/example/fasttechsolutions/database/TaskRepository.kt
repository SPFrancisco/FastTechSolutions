package com.example.fasttechsolutions.database

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    val allTask: LiveData<List<Task>> = taskDao.getAllTasks()!!

    /*suspend*/ fun insert(task: Task) {
        taskDao.insert(task)
    }

    /*suspend*/ fun update(task: Task) {
        taskDao.update(task)
    }

    /*suspend*/ fun delete(task: Task) {
        taskDao.detele(task)
    }
}