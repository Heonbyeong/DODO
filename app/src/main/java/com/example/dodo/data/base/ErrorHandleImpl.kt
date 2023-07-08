package com.example.dodo.data.base

import com.example.dodo.domain.base.ErrorEntity
import com.example.dodo.domain.base.ErrorHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorHandleImpl @Inject constructor() : ErrorHandler {

    override suspend fun <T> invoke(function: suspend () -> T): T =
        try {
            withContext(Dispatchers.IO) {
                function.invoke()
            }
        } catch (e: Throwable) {
            throw when (e) {
                is HttpException -> when (e.code()) {
                    HttpURLConnection.HTTP_BAD_REQUEST -> ErrorEntity.BadRequest
                    HttpURLConnection.HTTP_UNAUTHORIZED -> ErrorEntity.UnAuthorized
                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.Forbidden
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound
                    HttpURLConnection.HTTP_CONFLICT -> ErrorEntity.Conflict
                    in HttpURLConnection.HTTP_INTERNAL_ERROR..HttpURLConnection.HTTP_VERSION -> ErrorEntity.ServerError
                    else -> ErrorEntity.Unknown
                }
                is ConnectException,
                is SocketTimeoutException,
                is UnknownHostException -> ErrorEntity.NetworkError
                else -> ErrorEntity.Unknown
            }
        }
}