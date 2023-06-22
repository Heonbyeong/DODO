package com.example.dodo.data.features.home.todo.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "todoList")
data class Todo(
    val title: String,
    val location: String?,
    val date: LocalDate,
    val time: LocalTime?,
    val latitude: Float?,
    val longitude: Float?,
    val isNotify: Boolean,
    val isDone: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}