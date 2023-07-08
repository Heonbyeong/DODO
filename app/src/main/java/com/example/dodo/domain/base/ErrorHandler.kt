package com.example.dodo.domain.base

interface ErrorHandler {
    suspend operator fun<T> invoke(function: suspend () -> T): T
}