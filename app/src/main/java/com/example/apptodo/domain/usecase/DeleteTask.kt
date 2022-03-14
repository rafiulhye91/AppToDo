package com.example.apptodo.domain.usecase

import com.example.apptodo.data.model.Task
import com.example.apptodo.domain.repository.TaskRepository

class DeleteTask(private val repository: TaskRepository) {
    operator suspend fun invoke(task: Task) {
        repository.deleteTask(task)
    }
}
