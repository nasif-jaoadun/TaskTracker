package com.hishabee.tasktracker.data

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.hishabee.tasktracker.R
import com.hishabee.tasktracker.util.LOG_TAG
import com.hishabee.tasktracker.utils.TaskListingHelper
import com.hishabee.tasktracker.utils.Utility

class TaskRepository(val app: Application) {
    val taskData = MutableLiveData<List<Task>>()

    init{
        getTaskData()
    }

    private fun getTaskData() {

    }




}