package com.example.dodo.di.repository

import com.example.dodo.domain.features.home.todo.repository.TodoRepository
import com.example.dodo.data.features.home.todo.repository.TodoRepositoryImpl
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
}