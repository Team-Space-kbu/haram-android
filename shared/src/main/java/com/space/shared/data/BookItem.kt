package com.space.shared.data

data class BookItem<T>(
    val title: String? = "",
    val list: List<T> = emptyList(),
)