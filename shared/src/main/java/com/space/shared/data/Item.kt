package com.space.shared.data

data class Item<T, P>(
    val title: String? = null,
    val list: List<T> = emptyList(),
    val event: P? =null
)