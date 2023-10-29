package com.hishabee.tasktracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.hishabee.tasktracker.data.TaskRepository

class TaskListingsViewModal(app: Application): AndroidViewModel(app) {
    private val dataRepo = TaskRepository(app)
    val taskData = dataRepo.taskData

    fun refreshData() {
//        dataRepo.refreshDataFromWeb()
        dataRepo.getStaticTaskData()
    }
}