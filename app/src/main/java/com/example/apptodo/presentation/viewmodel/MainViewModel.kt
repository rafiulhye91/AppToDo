package com.example.apptodo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptodo.data.model.Task
import com.example.apptodo.domain.usecase.TaskUseCase
import com.example.apptodo.presentation.state.TaskListState
import com.example.apptodo.presentation.state.TaskState
import dagger.hilt.android.lifecycle.HiltViewModel
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
            _mTaskListState.value = TaskListState(isLoading = false, tasks = useCase.getAllTasks())
        }
    }

    public fun getTaskById(id: Int) {
        viewModelScope.launch {
            _mTaskState.value = TaskState(false, task = useCase.getTaskById(id))
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
