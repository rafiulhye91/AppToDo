package com.example.apptodo.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptodo.R
import com.example.apptodo.data.model.Task

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTask: TextView
        val textViewDate: TextView

        init {
            textViewTask = itemView.findViewById(R.id.text_task)
            textViewDate = itemView.findViewById(R.id.text_date)
        }
    }

    private var mList: List<Task> = emptyList()
    private var mListener: OnItemClickListener? = null

    fun setData(list: List<Task>) {
        mList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewTask.text = mList[position].title
        holder.textViewDate.text = mList[position].timestamp.toString()
        holder.itemView.setOnClickListener {
            mListener?.onItemClicked(mList.get(position))
        }
    }

    override fun getItemCount(): Int {
        if (mList == null) {
            return 0;
        }
        return mList.size
    }

    fun setListener(listener: OnItemClickListener) {
        mListener = listener
    }

    interface OnItemClickListener {
        fun onItemClicked(task: Task)
    }
}
