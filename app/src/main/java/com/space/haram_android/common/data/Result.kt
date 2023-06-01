package com.space.haram_android.common.data

sealed class Result<out T> {
    object Loading : Result<Nothing>()                                   // 상태값이 바뀌지 않는 서브 클래스의 경우 object 를 사용하는 것을 권장
    object UnLoading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val e: Throwable) : Result<Nothing>()
    data class Exception(val e: Throwable) : Result<Nothing>()
}