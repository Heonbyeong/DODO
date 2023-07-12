package com.example.dodo.data.features.home.todo.repository

import com.example.dodo.data.features.home.todo.database.toEntity
import com.example.dodo.data.features.home.todo.datasource.TodoLocalDataSource
import com.example.dodo.domain.entity.todo.TodoEntity
import com.example.dodo.domain.entity.todo.toDataEntity
import com.example.dodo.domain.param.TodoAddParam
import com.example.dodo.domain.param.toDataEntity
import com.example.dodo.domain.repository.todo.TodoRepository
import java.time.LocalDate
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoLocalDataSource: TodoLocalDataSource
) : TodoRepository {
    override suspend fun fetchTodoList(): List<TodoEntity> =
        todoLocalDataSource.fetchTodoList().map { it.toEntity() }

    override suspend fun fetchTodoListWithDate(targetDate: LocalDate): List<TodoEntity> =
        todoLocalDataSource.fetchTodoListWithDate(targetDate).map { it.toEntity() }

    override suspend fun fetchTodo(id: Int): TodoEntity =
        todoLocalDataSource.fetchTodo(id).toEntity()

    override suspend fun addTodo(todo: TodoAddParam) =
        todoLocalDataSource.addTodo(todo.toDataEntity())

    override suspend fun editTodo(todo: TodoAddParam) =
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