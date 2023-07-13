package com.example.dodo.data.features.home.todo.datasource

import com.example.dodo.data.features.home.todo.database.Todo
import com.example.dodo.data.features.home.todo.database.TodoDao
import java.time.LocalDate
import javax.inject.Inject

class TodoLocalDataSourceImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoLocalDataSource {
    override suspend fun fetchTodoList(): List<Todo> =
        todoDao.fetchTodoList()

    override suspend fun fetchTodoListWithDate(targetDate: LocalDate): List<Todo> =
        todoDao.fetchTodoListWithDate(targetDate)

    override suspend fun fetchTodo(id: Int): Todo =
        todoDao.fetchTodo(id)

    override suspend fun addTodo(todo: Todo) =
        todoDao.addTodo(todo)

    override suspend fun editTodo(todo: Todo) =
        todoDao.editTodo(todo)

    override suspend fun deleteTodo(todo: Todo) =
        todoDao.deleteTodo(todo)

    override suspend fun doneTodo(id: Int, isDone: Boolean) =
        todoDao.doneTodo(id, isDone)

    override suspend fun notifyChange(id: Int, isNotify: Boolean) =
        todoDao.notifyChange(id, isNotify)

    override suspend fun fetchTodoDetail(id: Int): Todo =
        todoDao.fetchTodoDetail(id)
}