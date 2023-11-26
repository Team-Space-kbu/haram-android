package result

sealed class ResultData<out T> {

    data class Success<T>(val body: T) : ResultData<T>()
    data class Unauthorized(val throwable: Throwable) : ResultData<Nothing>()
    data class Error(val throwable: Throwable) : ResultData<Nothing>()


}

fun <T> ResultData<T>.successOr(fallback: T): T {
    return (this as? ResultData.Success<T>)?.body ?: fallback
}

fun <T> ResultData<T>.succeeded(): T {
    return (this as? ResultData.Success<T>)?.body!!
}
