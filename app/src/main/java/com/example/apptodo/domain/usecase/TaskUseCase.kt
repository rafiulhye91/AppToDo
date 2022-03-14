package com.example.apptodo.domain.usecase

data class TaskUseCase(
    val deleteTask: DeleteTask,
    val getAllTasks: GetAllTasks,
    val insertTask: InsertTask,
    val updateTask: UpdateTask,
    val getTaskById: GetTaskById
)
