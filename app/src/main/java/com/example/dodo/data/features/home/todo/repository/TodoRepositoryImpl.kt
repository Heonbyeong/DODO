package com.example.dodo.data.features.home.todo.repository

import com.example.dodo.data.features.home.todo.database.Todo
import com.example.dodo.domain.features.home.todo.datasource.TodoLocalDataSource
import com.example.dodo.domain.features.home.todo.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoLocalDataSource: TodoLocalDataSource
): TodoRepository {
    override suspend fun fetchTodoList(): List<Todo> =
        todoLocalDataSource.fetchTodoList()

    override suspend fun addTodo(todo: Todo) =
        todoLocalDataSource.addTodo(todo)

    override suspend fun editTodo(todo: Todo) =
        todoLocalDataSource.editTodo(todo)

    override suspend fun deleteTodo(todo: Todo) =
        todoLocalDataSource.deleteTodo(todo)

    override suspend fun doneTodo(id: Int, isDone: Boolean) =
        todoLocalDataSource.doneTodo(id, isDone)

    override suspend fun notifyChange(id: Int, isNotify: Boolean) =
        todoLocalDataSource.notifyChange(id, isNotify)

    override suspend fun fetchTodoDetail(id: Int): Todo =
        todoLocalDataSource.fetchTodoDetail(id)
}