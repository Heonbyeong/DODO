package com.example.dodo.data.features.home.todo.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dodo.domain.entity.todo.TodoEntity
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "todoList")
data class Todo(
    val title: String,
    val location: String?,
    val date: LocalDate,
    val time: LocalTime?,
    val lat: Double?,
    val lng: Double?,
    val isNotify: Boolean,
    val isDone: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun Todo.toEntity() = TodoEntity(
    title = this.title,
    location = this.location,
    date = this.date,
    time = this.time,
    lat = this.lat,
    lng = this.lng,
    isNotify = this.isNotify,
    isDone = this.isDone
)