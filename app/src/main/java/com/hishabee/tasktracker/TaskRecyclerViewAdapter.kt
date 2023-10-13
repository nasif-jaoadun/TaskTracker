package com.hishabee.tasktracker

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hishabee.tasktracker.data.Task

class TaskRecyclerViewAdapter(val context: Context, val tasks: List<Task>) :
    RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder>()
{
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val taskName = itemView.findViewById<TextView>(R.id.tv_task_name)
        val statusImage = itemView.findViewById<ImageView>(R.id.iv_task_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.task_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        with(holder){
            taskName?.let {
                it.text = task.taskName
            }

            statusImage?.let {
                if(task.isDoneStatus)
                    it.setImageResource(R.drawable.ic_done_task)
                else
                    it.setImageResource(R.drawable.ic_task_todo)
            }
        }
    }
}