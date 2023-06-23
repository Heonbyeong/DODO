package com.example.dodo.domain.features.home.todo.datasource

import com.example.dodo.data.features.home.todo.database.Todo

interface TodoLocalDataSource {

    suspend fun fetchTodoList(): List<Todo>

    suspend fun addTodo(todo: Todo)

    suspend fun editTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    suspend fun doneTodo(id: Int, isDone: Boolean)

    suspend fun notifyChange(id: Int, isNotify: Boolean)

    suspend fun fetchTodoDetail(id: Int): Todo
}