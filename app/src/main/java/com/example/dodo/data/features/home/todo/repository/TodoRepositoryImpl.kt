package com.example.dodo.data.features.home.todo.repository

import com.example.dodo.data.features.home.todo.database.toEntity
import com.example.dodo.data.features.home.todo.datasource.TodoLocalDataSource
import com.example.dodo.domain.entity.todo.TodoEntity
import com.example.dodo.domain.entity.todo.toDataEntity
import com.example.dodo.domain.repository.todo.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoLocalDataSource: TodoLocalDataSource
) : TodoRepository {
    override suspend fun fetchTodoList(): List<TodoEntity> =
        todoLocalDataSource.fetchTodoList().map { it.toEntity() }

    override suspend fun addTodo(todo: TodoEntity) =
        todoLocalDataSource.addTodo(todo.toDataEntity())

    override suspend fun editTodo(todo: TodoEntity) =
        todoLocalDataSource.editTodo(todo.toDataEntity())

    override suspend fun deleteTodo(todo: TodoEntity) =
        todoLocalDataSource.deleteTodo(todo.toDataEntity())

    override suspend fun doneTodo(id: Int, isDone: Boolean) =
        todoLocalDataSource.doneTodo(id, isDone)

    override suspend fun notifyChange(id: Int, isNotify: Boolean) =
        todoLocalDataSource.notifyChange(id, isNotify)

    override suspend fun fetchTodoDetail(id: Int): TodoEntity =
        todoLocalDataSource.fetchTodoDetail(id).toEntity()
}