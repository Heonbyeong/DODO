package com.example.dodo.di.datasource

import com.example.dodo.data.features.home.todo.datasource.TodoLocalDataSource
import com.example.dodo.data.features.home.todo.datasource.TodoLocalDataSourceImpl
import com.example.dodo.data.features.home.todoadd.remote.datasource.TodoAddRemoteDataSource
import com.example.dodo.data.features.home.todoadd.remote.datasource.TodoAddRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun provideTodoLocalDataSource(
        todoLocalDataSourceImpl: TodoLocalDataSourceImpl
    ): TodoLocalDataSource

    @Singleton
    @Binds
    abstract fun provideTodoAddRemoteDataSource(
        todoAddRemoteDataSourceImpl: TodoAddRemoteDataSourceImpl
    ): TodoAddRemoteDataSource
}