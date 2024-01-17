package com.space.core_ui


fun interface NonParamsItemHandler {
    fun onClick()
}

fun interface ParamsItemHandler<T> {
    fun onClick(params: T)
}