package com.example.dodo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dodo.data.features.home.todo.database.Todo
import com.example.dodo.data.features.home.todo.database.TodoDao
import com.example.dodo.data.util.DateTimeConverter

@Database(entities = [Todo::class], version = 1)
@TypeConverters(DateTimeConverter::class)
abstract class DodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}