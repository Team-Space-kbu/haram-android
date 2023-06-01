package com.space.haram_android.common.data

data class ResponseBody<T>(
    val code: String,
    val description: String,
    val dateTime: String,
    val data: T
)