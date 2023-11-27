package com.space.shared.data.book

data class BookHome(
    val image: List<String>,
    val newBook: List<Category>,
    val bestBook: List<Category>,
    val rentalBook: List<Category>
)
