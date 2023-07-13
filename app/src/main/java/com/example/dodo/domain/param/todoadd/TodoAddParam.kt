package com.example.dodo.domain.param.todoadd

import com.example.dodo.data.features.home.todo.database.Todo
import com.example.dodo.domain.entity.todo.TodoEntity
import java.time.LocalDate
import java.time.LocalTime

data class TodoAddParam(
    val title: String,
    val location: String?,
    val date: LocalDate,
    val time: LocalTime?,
    val lat: Double?,
    val lng: Double?,
    val isNotify: Boolean,
    val isDone: Boolean
)

fun TodoAddParam.toDataEntity() = Todo(
    title = this.title,
    location = this.location,
    date = this.date,
    time = this.time,
    lat = this.lat,
    lng = this.lng,
    isNotify = this.isNotify,
    isDone = this.isDone
)

fun TodoAddParam.toTodoEntity() = TodoEntity(
    id = 0,
    title = this.title,
    location = this.location,
    date = this.date,
    time = this.time,
    lat = this.lat,
    lng = this.lng,
    isNotify = this.isNotify,
    isDone = this.isDone
)