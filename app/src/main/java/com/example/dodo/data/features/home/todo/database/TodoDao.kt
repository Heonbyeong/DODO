package com.example.dodo.data.features.home.todo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import java.time.LocalDate

@Dao
interface TodoDao {

    @Query("SELECT * FROM todoList")
    suspend fun fetchTodoList(): List<Todo>

    @Query("SELECT * FROM todoList WHERE date = :targetDate")
    suspend fun fetchTodoListWithDate(targetDate: LocalDate): List<Todo>

    @Query("SELECT * FROM todoList WHERE id = :id")
    suspend fun fetchTodo(id: Int) : Todo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodo(todo: Todo)

    @Update
    suspend fun editTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("UPDATE todoList SET isDone = :isDone WHERE id = :id")
    suspend fun doneTodo(id: Int, isDone: Boolean)

    @Query("UPDATE todoList SET isNotify = :isNotify WHERE id = :id")
    suspend fun notifyChange(id: Int, isNotify: Boolean)

    @Query("SELECT * FROM todoList WHERE id = :id")
    suspend fun fetchTodoDetail(id: Int): Todo
}