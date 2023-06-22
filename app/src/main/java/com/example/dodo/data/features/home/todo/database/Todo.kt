package com.example.dodo.data.features.home.todo.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "todoList")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val location: String?,
    val date: LocalDate,
    val time: LocalTime?,
    val lat: Float?,
    val lng: Float?,
    val isNotify: Boolean,
    val isDone: Boolean
) {
}