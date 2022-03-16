package com.example.apptodo.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.apptodo.R
import com.example.apptodo.data.local.utils.DateConverter
import com.example.apptodo.data.model.Task
import com.example.apptodo.databinding.FragmentNewTaskBinding
import com.example.apptodo.presentation.viewmodel.TaskViewModel


class NewTaskFragment : Fragment(R.layout.fragment_new_task) {

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
            if (mBinding.editTitle.text!!.length == 0) {
                Toast.makeText(context, "Please add a title", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val title = mBinding.editTitle.text.toString()
            val details = mBinding.editDetails.text.toString()
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