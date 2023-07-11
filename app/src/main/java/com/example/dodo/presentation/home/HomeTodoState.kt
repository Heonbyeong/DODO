package com.example.dodo.presentation.home

import com.example.dodo.domain.entity.todo.TodoEntity
import com.example.dodo.presentation.base.State
import java.time.LocalDate

data class HomeTodoState(
    val isLoading: Boolean = false,
    val selectedDate: LocalDate = LocalDate.now(),
    val todoList: List<TodoEntity> = emptyList(),
    val todoDetail: TodoEntity? = null,
) : State {
}