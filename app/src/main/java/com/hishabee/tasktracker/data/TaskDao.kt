package com.hishabee.tasktracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Query("SELECT * from tasks")
    fun getAll(): List<Task>

    @Insert
    suspend fun insertTask(task: Task)

    @Insert
    suspend fun insertTasks(tasks: List<Task>)

    @Query("DELETE from tasks")
    suspend fun deleteAll()

}