package com.example.apptodo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "details")
    val details: String?,
    @ColumnInfo(name = "timestamp")
    val timestamp: Long?
)
