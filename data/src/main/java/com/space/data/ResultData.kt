package com.space.data

sealed class ResultData<out T> {
    data class Success<T>(val body: T) : ResultData<T>()
    data class Unauthorized(val throwable: Throwable) : ResultData<Nothing>()
    data class Error(val throwable: Throwable) : ResultData<Nothing>()

}