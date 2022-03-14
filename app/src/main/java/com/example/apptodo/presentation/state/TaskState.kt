package com.example.apptodo.presentation.state

import com.example.apptodo.data.model.Task

data class TaskState(
    val isLoading: Boolean = false,
    val error: String? = "",
    val task: Task? = null
)
