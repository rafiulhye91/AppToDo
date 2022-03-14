package com.example.apptodo.data.repository

import com.example.apptodo.data.local.TaskDao
import com.example.apptodo.data.model.Task
import com.example.apptodo.domain.repository.TaskRepository

class TaskRepositoryImpl(private val taskDao:TaskDao):TaskRepository {
    override suspend fun getAllTasks(): List<Task> {
        return taskDao.getAllTasks()
    }

    override suspend fun getTaskById(id: Int): Task {
        return taskDao.getTaskById(id)
    }

    override suspend fun insertTask(task: Task) {
        return taskDao.insertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        return taskDao.deleteTask(task)
    }

    override suspend fun updateTask(task: Task) {
        return taskDao.updateTask(task)
    }
}