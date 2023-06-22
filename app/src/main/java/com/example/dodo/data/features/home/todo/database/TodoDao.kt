package com.example.dodo.data.features.home.todo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {

    @Query("SELECT * FROM todoList")
    suspend fun fetchTodoList(): List<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodo(todo: Todo)

    @Update
    suspend fun editTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("UPDATE todoList SET isDone = :isDone WHERE id = :id")
    suspend fun doneTodo(id: Long, isDone: Boolean = true)

    @Query("UPDATE todoList SET isNotify = :isNotify WHERE id = :id")
    suspend fun notifyChange(id: Long, isNotify: Boolean = false)

    @Query("SELECT * FROM todoList WHERE id = :id")
    suspend fun fetchTodoDetail(id: Long)
}