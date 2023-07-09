package com.example.dodo.data.features.home.todo.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dodo.domain.entity.todo.TodoEntity
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
)

fun Todo.toEntity() = TodoEntity(
    id = this.id,
    title = this.title,
    location = this.location,
    date = this.date,
    time = this.time,
    lat = this.lat,
    lng = this.lng,
    isNotify = this.isNotify,
    isDone = this.isDone
)