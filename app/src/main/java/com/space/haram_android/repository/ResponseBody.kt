package com.space.haram_android.repository

data class ResponseBody<T>(
    val code: String,
    val description: String,
    val dateTime: String,
    val data: T
)