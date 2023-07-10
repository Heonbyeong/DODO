package com.example.dodo.domain.usecase.base

abstract class BaseUseCase<P, R> {
    abstract suspend operator fun invoke(data: P): R
}