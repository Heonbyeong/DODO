package com.example.dodo.presentation.home

import com.example.dodo.data.features.home.todo.database.Todo
import com.example.dodo.presentation.base.State
import java.time.LocalDate

data class HomeTodoState(
    val selectedDate: LocalDate = LocalDate.now(),
    val todo: Todo? = null,
) : State {
}