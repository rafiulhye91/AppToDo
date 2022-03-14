package com.example.apptodo.domain.usecase

import com.example.apptodo.common.Resource
import com.example.apptodo.data.model.Task
import com.example.apptodo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTaskById @Inject constructor(private val repository: TaskRepository) {
    operator suspend fun invoke(id: Int): Flow<Resource<Task>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getTaskById(id)
            emit(Resource.Success(data = response))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage))
        }
    }
}
