package com.space.haram_android.usecase

data class ResponseBody<T>(
    val code: String,
    val description: String,
    val dateTime: String,
    val data: T
)