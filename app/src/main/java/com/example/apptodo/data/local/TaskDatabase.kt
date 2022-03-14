package com.example.apptodo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apptodo.data.model.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao

    companion object {
        const val DATABASE_NAME = "task_db"
    }
}