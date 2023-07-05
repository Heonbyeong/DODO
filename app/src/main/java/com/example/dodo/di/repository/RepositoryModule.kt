package com.example.dodo.di.repository

import com.example.dodo.domain.repository.todo.TodoRepository
import com.example.dodo.data.features.home.todo.repository.TodoRepositoryImpl
import com.example.dodo.data.features.home.todoadd.repository.TodoAddRepositoryImpl
import com.example.dodo.domain.repository.todoadd.TodoAddRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideTodoRepository(
        todoRepositoryImpl: TodoRepositoryImpl
    ): TodoRepository

    @Singleton
    @Binds
    abstract fun provideTodoAddRepository(
        todoAddRepositoryImpl: TodoAddRepositoryImpl
    ): TodoAddRepository
}