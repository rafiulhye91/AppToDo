package com.example.apptodo.domain.usecase

import com.example.apptodo.common.Resource
import com.example.apptodo.data.model.Task
import com.example.apptodo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllTasks(private val repository: TaskRepository) {
    operator suspend fun invoke(): Flow<Resource<List<Task>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getAllTasks()
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage))
        }

    }
}
