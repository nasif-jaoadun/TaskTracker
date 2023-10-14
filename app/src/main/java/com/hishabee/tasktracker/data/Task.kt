package com.hishabee.tasktracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val taskId: Int,
    val taskName: String,
    val isDoneStatus: Boolean
)