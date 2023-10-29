package com.hishabee.tasktracker.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.hishabee.tasktracker.util.LOG_TAG
import com.hishabee.tasktracker.util.WEB_SERVICE_URL
import com.hishabee.tasktracker.utils.FileHelper
import com.hishabee.tasktracker.utils.Utility
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TaskRepository(val app: Application) {
    val taskData = MutableLiveData<List<Task>>()
    private val taskDao = TaskDatabase.getDatabase(app)
        .taskDao()

    init{
        CoroutineScope(Dispatchers.IO).launch {
            val data = taskDao.getAll()
            if (data.isEmpty()) {
                callWebService()
            } else {
                taskData.postValue(data)
                withContext(Dispatchers.Main) {
                    Toast.makeText(app, "Using local data", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    @WorkerThread
    suspend fun callWebService(){
        if (Utility.networkAvailable(app)) {
            withContext(Dispatchers.Main) {
                Toast.makeText(app, "Using remote data", Toast.LENGTH_LONG).show()
            }
            Log.i(LOG_TAG, "Calling web service")
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(TaskService::class.java)
            val serviceData = service.getTaskData().body() ?: getTaskDataFromAsset(app)
            taskData.postValue(serviceData)
            taskDao.deleteAll()
            taskDao.insertTasks(serviceData)
        }
    }

    fun getStaticTaskData(){
        val staticTaskData = getTaskDataFromAsset(app)
        taskData.postValue(staticTaskData)
    }



    fun refreshDataFromWeb() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }

    private val listType = Types.newParameterizedType(
        List::class.java, Task::class.java
    )

    fun getTaskDataFromAsset(context: Context): List<Task> {
        val text = FileHelper.getTextFromAssets(context, "task_data.json")
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<Task>> =
            moshi.adapter(listType)
        return adapter.fromJson(text) ?: emptyList()
    }
}