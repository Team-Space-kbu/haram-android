package com.space.data

data class SpaceBody<T>(
    val code: String,
    val description: String,
    val dateTime: String,
    val data: T
)