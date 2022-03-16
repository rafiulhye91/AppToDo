package com.example.apptodo.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptodo.R
import com.example.apptodo.common.Constants.TAG
import com.example.apptodo.data.model.Task
import com.example.apptodo.databinding.FragmentTaskListBinding
import com.example.apptodo.presentation.ui.adapter.CustomAdapter
import com.example.apptodo.presentation.viewmodel.EditTaskViewModel
import com.example.apptodo.presentation.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListFragment : Fragment(R.layout.fragment_task_list), CustomAdapter.OnItemClickListener {

    private lateinit var mBinding: FragmentTaskListBinding
    private val mTaskViewModel: TaskViewModel by activityViewModels()
    private val mEditTaskViewModel: EditTaskViewModel by activityViewModels()
    private lateinit var mCustomAdapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentTaskListBinding.inflate(layoutInflater)

        mBinding.fab.setOnClickListener {
            launchFragment(NewTaskFragment())
        }
        mCustomAdapter = CustomAdapter()
        setRecyclerView()
        return mBinding.root
    }

    private fun setRecyclerView() {
        mCustomAdapter.setListener(this)
        mBinding.recyclerViewTasks.adapter = mCustomAdapter
        mBinding.recyclerViewTasks.layoutManager = LinearLayoutManager(context)
        val swipeToDeleteCallback = object : CustomAdapter.SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                val task = mCustomAdapter.removeData(pos)
                mTaskViewModel.deleteTask(task)
                mCustomAdapter.notifyItemRemoved(pos)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(mBinding.recyclerViewTasks)
    }

    override fun onResume() {
        super.onResume()
        mTaskViewModel.taskListState.observe(
            viewLifecycleOwner, Observer {
                if (!it.tasks.isNullOrEmpty()) {
                    Log.d(TAG, it.tasks.toString())
                    mCustomAdapter.setData(it.tasks)
                    mCustomAdapter.notifyDataSetChanged()
                }
            }
        )
        mTaskViewModel.getAllTasks()
    }

    private fun launchFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in,
            R.anim.slide_out,
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onItemClicked(task: Task) {
        launchFragment(EditTaskFragment())
        mEditTaskViewModel.setTask(task)
        return
    }

}