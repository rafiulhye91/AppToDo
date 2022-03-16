package com.example.apptodo.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.apptodo.R
import com.example.apptodo.data.local.utils.DateConverter
import com.example.apptodo.data.model.Task
import com.example.apptodo.databinding.FragmentNewTaskBinding
import com.example.apptodo.presentation.viewmodel.TaskViewModel


class NewTaskFragment : BaseFragments(R.layout.fragment_new_task) {

    private lateinit var mBinding: FragmentNewTaskBinding
    private val mViewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentNewTaskBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        setUI()
    }

    private fun setUI() {
        mBinding.btnAdd.setOnClickListener {
            if (mBinding.newTitle.text!!.length == 0) {
                Toast.makeText(context, "Please add a title", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val title = mBinding.newTitle.text.toString()
            val details = mBinding.newDetails.text.toString()
            mViewModel.addTask(
                Task(
                    title = title,
                    details = details,
                    timestamp = DateConverter.toDate(System.currentTimeMillis())
                )
            )
            finishTheFragment()
        }

        mBinding.btnCancel.setOnClickListener {
            finishTheFragment()
        }
    }
}