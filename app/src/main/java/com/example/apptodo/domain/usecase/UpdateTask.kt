package com.example.apptodo.domain.usecase

import com.example.apptodo.data.model.Task
import com.example.apptodo.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTask @Inject constructor(private val repository: TaskRepository) {
    operator suspend fun invoke(task: Task) {
        repository.updateTask(task)
    }
}
