package com.space.haram_android.data

import java.util.Objects

data class ResponseBody<T>(
    val code: String, val description: String, val dateTime: String, val data: T
)