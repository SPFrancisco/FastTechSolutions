package com.example.fasttechsolutions.database

import androidx.room.*

@Entity(tableName = "Tasks_table")
data class Task (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "time_stamp")
    var timeStamp: String
)
