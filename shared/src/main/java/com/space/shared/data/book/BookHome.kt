package com.space.shared.data.book

data class BookHome(
    val image: List<String> = emptyList(),
    val newBook: List<Category> = emptyList(),
    val bestBook: List<Category> = emptyList(),
    val rentalBook: List<Category> = emptyList()
)
