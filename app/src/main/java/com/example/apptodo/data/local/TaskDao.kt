package com.example.apptodo.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.apptodo.data.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    suspend fun getAllTasks(): List<Task>

    @Query("SELECT * FROM task WHERE id ==:id")
    suspend fun getTaskById(id: Int): Task

    @Insert(onConflict = REPLACE)
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)
}
