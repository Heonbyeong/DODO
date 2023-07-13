package com.example.dodo.domain.repository.todo

import com.example.dodo.domain.entity.todo.TodoEntity
import com.example.dodo.domain.param.todo.EditTodoParam
import com.example.dodo.domain.param.todoadd.TodoAddParam
import java.time.LocalDate

interface TodoRepository {

    suspend fun fetchTodoList(): List<TodoEntity>

    suspend fun fetchTodoListWithDate(targetDate: LocalDate): List<TodoEntity>

    suspend fun fetchTodo(id: Int): TodoEntity

    suspend fun addTodo(todo: TodoAddParam)

    suspend fun editTodo(todo: EditTodoParam)

    suspend fun deleteTodo(todo: TodoEntity?)

    suspend fun doneTodo(id: Int, isDone: Boolean)

    suspend fun notifyChange(id: Int, isNotify: Boolean)

    suspend fun fetchTodoDetail(id: Int): TodoEntity
}