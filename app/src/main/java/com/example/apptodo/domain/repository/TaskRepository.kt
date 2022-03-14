package com.example.apptodo.domain.repository

import com.example.apptodo.data.model.Task

interface TaskRepository {
    suspend fun getAllTasks(): List<Task>
    suspend fun getTaskById(id: Int): Task
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateTask(task: Task)
}
