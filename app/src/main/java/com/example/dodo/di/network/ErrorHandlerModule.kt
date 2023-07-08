package com.example.dodo.di.network

import com.example.dodo.data.base.ErrorHandleImpl
import com.example.dodo.domain.base.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class ErrorHandlerModule {

    @Binds
    abstract fun provideErrorHandler(
        errorHandleImpl: ErrorHandleImpl
    ): ErrorHandler
}