package com.example.fasttechsolutions.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    /*suspend*/ fun insert(task: Task)

    @Query("SELECT * FROM tasks_table ORDER BY id ASC")
    fun getAllTasks(): LiveData<List<Task>>

//    @Query("SELECT * FROM tasks_table WHERE title LIKE :title")
//    fun getUserByTitle(title: String): LiveData<List<Task>>?

    @Update
    /*suspend*/ fun update(task: Task)

    @Delete
    /*suspend*/ fun detele(task: Task)
}