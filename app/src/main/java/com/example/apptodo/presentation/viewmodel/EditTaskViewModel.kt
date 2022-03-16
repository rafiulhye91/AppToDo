package com.example.apptodo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apptodo.data.model.Task

class EditTaskViewModel : ViewModel() {
    private val _mTask: MutableLiveData<Task> = MutableLiveData()
    val mTask: LiveData<Task> = _mTask

    fun setTask(task: Task) {
        _mTask.postValue(task)
    }

}