package com.space.shared.data.book

data class BookSearch(
    val start: Int,
    val end: Int,
    val result: List<Search>
)
