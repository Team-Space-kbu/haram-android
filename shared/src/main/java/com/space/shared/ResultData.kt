package com.space.shared

sealed class ResultData<out T> {

    data class Success<T>(val body: T) : ResultData<T>()
    data class Error(val throwable: Throwable) : ResultData<Nothing>()
    object Loading : ResultData<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[body=$body]"
            is Error -> "Error[exception=$throwable]"
            is Loading -> "Loading"
        }
    }
}

fun <T> ResultData<T>.successOr(fallback: T): T {
    return (this as? ResultData.Success<T>)?.body ?: fallback
}

fun <T> ResultData<T>.succeeded(): T? {
    return (this as? ResultData.Success<T>)?.body
}

val <T> ResultData<T>.data: T?
    get() = (this as? ResultData.Success)?.body

inline fun <R, T> ResultData<T>.map(onSuccess: (T) -> R): ResultData<R> {
    return when (this) {
        is ResultData.Success -> ResultData.Success(onSuccess(body))
        is ResultData.Error -> ResultData.Error(throwable)
        ResultData.Loading -> ResultData.Loading
    }
}

inline fun <R, T> ResultData<T>.mapCatching(
    onSuccess: (T) -> R,
    onError: (Throwable) -> R
): ResultData<R> {
    return when (this) {
        is ResultData.Success -> ResultData.Success(onSuccess(body))
        is ResultData.Error -> {
            onError(throwable)
            ResultData.Error(throwable)
        }
        ResultData.Loading -> ResultData.Loading
    }
}