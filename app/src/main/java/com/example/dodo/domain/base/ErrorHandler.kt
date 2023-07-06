package com.example.dodo.domain.base

interface ErrorHandler {
    suspend operator fun<T> invoke(func: suspend () -> T): T
}