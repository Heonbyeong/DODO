package com.example.dodo.di.database

import android.content.Context
import androidx.room.Room
import com.example.dodo.data.features.home.todo.database.TodoDao
import com.example.dodo.data.local.database.DodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDodoDatabase(
        @ApplicationContext context: Context
    ): DodoDatabase = Room
        .databaseBuilder(context, DodoDatabase::class.java, "dodo_database")
        .build()

    @Singleton
    @Provides
    fun provideTodoDao(dodoDatabase: DodoDatabase): TodoDao = dodoDatabase.todoDao()
}