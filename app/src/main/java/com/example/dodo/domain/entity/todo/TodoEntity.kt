package com.example.dodo.domain.entity.todo

import com.example.dodo.data.features.home.todo.database.Todo
import java.time.LocalDate
import java.time.LocalTime

data class TodoEntity(
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

fun TodoEntity.toDataEntity() = Todo(
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
