package com.example.apptodo.domain.usecase

import com.example.apptodo.data.model.Task
import com.example.apptodo.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskById @Inject constructor(private val repository: TaskRepository) {
    suspend operator fun invoke(id: Int): Task {
        return repository.getTaskById(id)
    }
}
