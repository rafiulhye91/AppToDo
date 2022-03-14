package com.example.apptodo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptodo.common.Resource
import com.example.apptodo.data.model.Task
import com.example.apptodo.domain.usecase.TaskUseCase
import com.example.apptodo.presentation.state.TaskListState
import com.example.apptodo.presentation.state.TaskState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: TaskUseCase) : ViewModel() {

    private val _mTaskListState: MutableLiveData<TaskListState> = MutableLiveData<TaskListState>()
    private val _mTaskState: MutableLiveData<TaskState> = MutableLiveData<TaskState>()
    val taskListState: LiveData<TaskListState> = _mTaskListState
    val taskState: LiveData<TaskState> = _mTaskState

    public fun getAllTasks() {
        viewModelScope.launch {
            useCase.getAllTasks().onEach {
                if (it is Resource.Loading) {
                    _mTaskListState.value = TaskListState(isLoading = true)
                }
                if (it is Resource.Success) {
                    _mTaskListState.value = TaskListState(isLoading = false, tasks = it.data)

                }
                if (it is Resource.Error) {
                    _mTaskListState.value = TaskListState(isLoading = false, error = it.errMessage)
                }
            }
        }
    }

    public fun getTaskById(id: Int) {
        viewModelScope.launch {
            useCase.getTaskById(id).onEach {
                if (it is Resource.Loading) {
                    _mTaskState.value = TaskState(isLoading = true)
                }
                if (it is Resource.Success) {
                    _mTaskState.value = TaskState(isLoading = false, task = it.data)

                }
                if (it is Resource.Error) {
                    _mTaskState.value = TaskState(isLoading = false, error = it.errMessage)
                }
            }
        }
    }

    public fun addTask(task: Task) {
        viewModelScope.launch {
            useCase.insertTask(task)
        }
    }

    public fun deleteTask(task: Task) {
        viewModelScope.launch {
            useCase.deleteTask(task)
        }
    }

    public fun updateTask(task: Task) {
        viewModelScope.launch {
            useCase.updateTask(task)
        }
    }
}
