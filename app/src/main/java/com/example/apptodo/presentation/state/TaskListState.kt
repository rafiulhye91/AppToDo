package com.example.apptodo.presentation.state

import com.example.apptodo.data.model.Task

data class TaskListState(
    val isLoading: Boolean = false,
    val error: String? = "",
    val tasks: List<Task>? = emptyList()
)
