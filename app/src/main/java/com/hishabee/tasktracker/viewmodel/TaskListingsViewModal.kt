package com.hishabee.tasktracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hishabee.tasktracker.R
import com.hishabee.tasktracker.data.TaskRepository
import com.hishabee.tasktracker.util.LOG_TAG
import com.hishabee.tasktracker.utils.TaskListingHelper
import com.hishabee.tasktracker.utils.Utility

class TaskListingsViewModal(app: Application): AndroidViewModel(app) {
    private val dataRepo = TaskRepository(app)
    val taskData = dataRepo.taskData

}