package com.example.apptodo.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.apptodo.R
import com.example.apptodo.common.Constants.TAG
import com.example.apptodo.databinding.FragmentTaskListBinding
import com.example.apptodo.presentation.viewmodel.TaskListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListFragment :Fragment(R.layout.fragment_task_list){

    lateinit var mBinding:FragmentTaskListBinding
    private val mViewModel:TaskListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentTaskListBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.taskListState.observe(
            viewLifecycleOwner, Observer {
                if(!it.tasks.isNullOrEmpty()){
                    Log.d(TAG, it.tasks.toString())
                }
            }
        )
    }


}