package com.example.dodo.domain.base

import java.lang.RuntimeException

sealed class ErrorEntity : RuntimeException() {
    object NetworkError : ErrorEntity()
    object BadRequest : ErrorEntity()
    object UnAuthorized : ErrorEntity()
    object Forbidden : ErrorEntity()
    object NotFound : ErrorEntity()
    object Conflict : ErrorEntity()
    object ServerError : ErrorEntity()
    object Unknown : ErrorEntity()
}
