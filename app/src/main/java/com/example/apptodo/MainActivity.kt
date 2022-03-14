package com.example.apptodo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.apptodo.data.model.Task
import com.example.apptodo.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val task = Task(title = "Night", details = "It is already late")

        viewModel.addTask(task)
        viewModel.getAllTasks()
        viewModel.taskListState.observe(this, Observer {
            Log.d(
                "AppToDo",
                it.isLoading.toString() + " " + it.tasks.toString() + " " + it.error.toString()
            )
        })

    }
}