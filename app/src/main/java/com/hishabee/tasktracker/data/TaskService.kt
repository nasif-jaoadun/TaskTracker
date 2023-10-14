package com.hishabee.tasktracker.data

import retrofit2.Response
import retrofit2.http.GET

interface TaskService {
    @GET("/index/task_data.json")
    suspend fun getTaskData(): Response<List<Task>>
}