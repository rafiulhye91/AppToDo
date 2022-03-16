package com.example.apptodo.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.apptodo.R
import com.example.apptodo.data.local.utils.DateConverter
import com.example.apptodo.data.model.Task
import com.example.apptodo.databinding.FragmentEditTaskBinding
import com.example.apptodo.presentation.viewmodel.EditTaskViewModel
import com.example.apptodo.presentation.viewmodel.TaskViewModel

class EditTaskFragment : Fragment(R.layout.fragment_edit_task) {

    private lateinit var mBinding: FragmentEditTaskBinding
    private val mTaskViewModel: TaskViewModel by activityViewModels()
    private val mEditTaskViewModel: EditTaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentEditTaskBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        mEditTaskViewModel.mTask.observe(viewLifecycleOwner, Observer {
            setUI(it);
        })
    }

    private fun setUI(task: Task) {
        mBinding.textTimestamp.setText(task.timestamp.toString())
        mBinding.editTitle.setText(task.title.toString())
        mBinding.editDetails.setText(task.details.toString())
        mBinding.btnCancel.setOnClickListener {
            finishTheFragment()
        }
        mBinding.btnSave.setOnClickListener {
            val updatedTask: Task = Task(
                task.id,
                mBinding.editTitle.text.toString(),
                mBinding.editDetails.text.toString(),
                DateConverter.toDate(System.currentTimeMillis())
            )
            mTaskViewModel.updateTask(updatedTask)
            finishTheFragment()
        }
        mBinding.btnCancel.setOnClickListener {
            finishTheFragment()
        }
    }

    private fun finishTheFragment() {
        hideSoftKeyBoard(context, view)
        val manager = requireActivity().supportFragmentManager
        manager.popBackStack()
    }

    fun hideSoftKeyBoard(context: Context?, view: View?) {
        try {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (view != null) {
                imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}