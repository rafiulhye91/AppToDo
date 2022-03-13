package com.example.apptodo.data.local

import androidx.room.Database
import com.example.apptodo.data.model.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase {
    abstract fun getTaskDao(): TaskDao
}